package com.st.recommender.recommenders.label;

import com.st.recommender.common.utils.DataUtils;
import com.st.recommender.common.utils.TaskPool;
import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.BaseEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.model.opt.labelbase.ItemLabelsGenerator;
import com.st.recommender.model.opt.labelbase.SeedProfile;
import com.st.recommender.service.DataProcessor;
import com.st.recommender.service.PredictService;
import com.st.recommender.service.abstractgroup.AbstractPredictServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LabelBasePredictServiceImpl extends AbstractPredictServiceImpl implements PredictService, LabelBasePredictService, DataProcessor {

    @Override
    public String predict(Param param) {
        AlgorithmEnum algorithm = param.getAlgorithm();

        Map oriData = Optional.ofNullable(dataLoaderMap.get(algorithm))
                .flatMap(service -> Optional.ofNullable(service.loadData(param))).orElse(new HashMap<>());
        if (CollectionUtils.isEmpty(oriData)) {
            return "";
        }
        log.info("Got {} original data", oriData.size());

        Map cleanData = (Map) Optional.ofNullable(dataConverterMap.get(algorithm))
                .flatMap(service -> Optional.ofNullable(service.convert(oriData))).orElse(new HashMap<>());
        if (CollectionUtils.isEmpty(cleanData)) {
            return "";
        }
        log.info("Got {} clean data", cleanData.size());

        Map<Class, List> normalData = (Map<Class, List>) Optional.ofNullable(dataNormalizerMap.get(algorithm))
                .flatMap(service -> Optional.ofNullable(service.normalize(param, cleanData)))
                .orElse(new HashMap<>());
        if (CollectionUtils.isEmpty(normalData)) {
            return "";
        }

        log.info("Got {} normalized data", normalData.size());
        Map<String, List<String>> predictResult = Optional.ofNullable(dataPredictorMap.get(algorithm))
                .flatMap(service -> Optional.ofNullable(((LabelBasePredictServiceImpl) service).doPredict(param, normalData))).orElse(new HashMap<>());
        if (CollectionUtils.isEmpty(predictResult)) {
            return "";
        }

        String filePath = Optional.ofNullable(dataOutputServiceMap.get(algorithm))
                .flatMap(service -> Optional.ofNullable(service.outputAsFile(param, predictResult))).orElse("");
        log.info("Write result into file:[{}]", filePath);
        return filePath;
    }

    @Override
    public Map<String, List<String>> doPredict(Param param, Map<Class, List> normalData) {
        Map<String, List<String>> map = new HashMap<>();
        List<SeedProfile> seedProfiles = normalData.get(SeedProfile.class);
        List<ItemLabelsGenerator> itemMatrices = normalData.get(ItemLabelsGenerator.class);
        if (DataUtils.isAnyCollectionEmpty(seedProfiles, itemMatrices)) {
            return map;
        }

        long start = System.nanoTime();
        Executor executor = TaskPool.getExecutor();
        return predictTask(param, normalData, executor)
                .whenCompleteAsync((predictMap, e) -> {
                    log.info("Took [{}] to build the predict map with [{}] items"
                            , (System.nanoTime() - start) / 1_000_000
                            , CollectionUtils.isEmpty(predictMap) ? 0 : predictMap.size());
                    if (Objects.nonNull(e)) {
                        log.error("Got error:[{}]", e);
                    }
                }).join();
    }

    protected CompletableFuture<ConcurrentHashMap<String, List<String>>> predictTask(Param param, Map<Class, List> normalData, Executor executor) {
        return CompletableFuture.supplyAsync(() -> (Map<String, List<MutablePair<Long, Long>>>) buildSeedMatrices(normalData.get(SeedProfile.class)), executor)
                .thenCombineAsync(CompletableFuture.supplyAsync(() -> (Map<Long, double[]>) buildItemMatrices(normalData.get(ItemLabelsGenerator.class)), executor)
                        , (seedMap, itemMatrixMap) -> predictForItem(seedMap, itemMatrixMap, param));
    }

    protected ConcurrentHashMap<String, List<String>> predictForItem(
            Object seed
            , Object items
            , Param param) {
        long start = System.nanoTime();

        Map<String, List<MutablePair<Long, Long>>> seedMap = (Map<String, List<MutablePair<Long, Long>>>) seed;
        Map<Long, double[]> itemMatrixMap = (Map<Long, double[]>) items;

        ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();
        List<String> seedList = Optional.ofNullable(param.getSeedList()).orElse(new ArrayList<>());
        List<String> workList = CollectionUtils.isEmpty(seedList) ? new ArrayList<>(seedMap.keySet()) : seedList;

        int workParallelism = workParallel().apply(param);
        boolean selfDistinct = selfDistinct().apply(param);
        BiFunction<double[], double[], Double> similarityCalc = similarityCalc().apply(param);

        Integer topN = param.getTopN();
        Integer topSimilarity = param.getTopSimilarity();

        ForkJoinPool forkJoinPool = new ForkJoinPool(workParallelism);
        forkJoinPool.submit(() ->
                workList.parallelStream().filter(seedMap::containsKey).forEach(id -> {
                    List<MutablePair<Long, Long>> labelList = seedMap.get(id);
                    List<MutablePair<Long, Double>> scoreList = labelList.stream()
                            .filter(pair -> itemMatrixMap.containsKey(pair.getKey()))
                            .limit(topN)
                            .map(pair -> {
                                Long itemId = pair.getKey();
                                Long count = pair.getValue();

                                double[] itemMatrix = itemMatrixMap.get(itemId);
                                return itemMatrixMap.entrySet().stream()
                                        .filter(item -> !selfDistinct || !itemId.equals(item.getKey()))
                                        .map(e -> MutablePair.of(e.getKey(), similarityCalc.apply(e.getValue(), itemMatrix)))
                                        .sorted(Comparator.comparing(MutablePair::getValue))
                                        .limit(topSimilarity)
                                        .peek(simPair -> simPair.setValue(simPair.getValue() * count))
                                        .collect(Collectors.toList());
                            })
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList());

                    ConcurrentHashMap<Long, Double> finalScore = new ConcurrentHashMap<>();
                    scoreList.stream()
                            .peek(pair -> finalScore.putIfAbsent(pair.getKey(), 0D))
                            .forEach(pair -> finalScore.put(pair.getKey(), finalScore.get(pair.getKey()) + pair.getValue()));

                    List<String> resultList = finalScore.entrySet().stream()
                            .sorted(Map.Entry.comparingByValue())
                            .limit(topN)
                            .map(Map.Entry::getKey)
                            .map(Object::toString)
                            .collect(Collectors.toList());
                    map.put(id, resultList);
                })
        ).join();

        log.info("Took [{}] to build a [{}] recommend", (System.nanoTime() - start) / 1_000_000, map.size());
        return map;
    }

    protected Map<String, List<MutablePair<Long, Long>>> buildSeedMatrices(List<SeedProfile> seedProfiles) {
        long start = System.nanoTime();
        Map<String, List<MutablePair<Long, Long>>> map = seedProfiles.stream()
                .map(matrix -> MutablePair.of(matrix.getId(), matrix.getItems()))
                .collect(Collectors.toMap(MutablePair::getKey, MutablePair::getValue));
        log.info("Took [{}] to build a [{}] seedMatricesMap", (System.nanoTime() - start) / 1_000_000, map.size());
        return map;
    }

    protected Map<Long, double[]> buildItemMatrices(List<ItemLabelsGenerator> itemMatrices) {
        long start = System.nanoTime();
        Map<Long, double[]> map = itemMatrices.stream()
                .map(matrix -> MutablePair.of(matrix.getIdx(), matrix.getMatrix()))
                .collect(Collectors.toMap(MutablePair::getKey, MutablePair::getValue));
        log.info("Took [{}] to build a [{}] itemMatricesMap", (System.nanoTime() - start) / 1_000_000, map.size());
        return map;
    }

    @Override
    public BaseEnum algorithm() {
        return AlgorithmEnum.LABEL_BASE;
    }
}
