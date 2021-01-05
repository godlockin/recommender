package com.st.recommender.recommenders.als;

import com.st.recommender.common.utils.DataUtils;
import com.st.recommender.common.utils.TaskPool;
import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.BaseEnum;
import com.st.recommender.constants.Constants.AlgorithmMapping;
import com.st.recommender.constants.DistanceEnum;
import com.st.recommender.constants.NormalizationEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.model.opt.als.ScoreModel;
import com.st.recommender.service.*;
import com.st.recommender.service.abstractgroup.AbstractPredictServiceImpl;
import com.st.recommender.service.common.CommonFuncUtils;
import com.st.recommender.service.common.DistanceFuncUtils;
import com.st.recommender.service.common.NormalizeFuncUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public abstract class AlsPredictServiceImpl extends AbstractPredictServiceImpl implements PredictService, AlsPredictService, DataProcessor {

    @Override
    public String predict(Param param) {
        AlgorithmEnum algorithm = param.getAlgorithm();

        Map oriData = Optional.ofNullable(dataLoaderMap.get(algorithm))
                .flatMap(service -> Optional.ofNullable(service.loadData(param))).orElse(new HashMap<>());
        if (CollectionUtils.isEmpty(oriData)) {
            return "";
        }
        log.info("Got {} original data", oriData.size());

        List cleanData = (List) Optional.ofNullable(dataConverterMap.get(algorithm))
                .flatMap(service -> Optional.ofNullable(service.convert(oriData)))
                .orElse(new ArrayList<>());
        if (CollectionUtils.isEmpty(cleanData)) {
            return "";
        }
        log.info("Got {} clean data", cleanData.size());

        List normalData = (List) Optional.ofNullable(dataNormalizerMap.get(algorithm))
                .flatMap(service -> Optional.ofNullable(service.normalize(param, cleanData)))
                .orElse(new ArrayList());
        if (CollectionUtils.isEmpty(normalData)) {
            return "";
        }

        log.info("Got {} normalized data", normalData.size());
        Map<String, List<String>> predictResult = (Map<String, List<String>>) Optional.ofNullable(dataPredictorMap.get(algorithm))
                .filter(service -> service instanceof AlsPredictServiceImpl)
                .flatMap(service -> Optional.ofNullable(((AlsPredictServiceImpl) service)
                        .doPredict(param, normalData))).orElse(new HashMap<>());
        if (CollectionUtils.isEmpty(predictResult)) {
            return "";
        }

        String filePath = Optional.ofNullable(dataOutputServiceMap.get(algorithm))
                .flatMap(service -> Optional.ofNullable(service.outputAsFile(param, predictResult))).orElse("");
        log.info("Write result into file:[{}]", filePath);
        return filePath;
    }

    @Override
    public Map<String, List<String>> doPredict(Param param, List<List<ScoreModel>> normalData) {
        Map<String, List<String>> map = new HashMap<>();
        List<ScoreModel> workList = Optional.ofNullable(normalData).orElse(new ArrayList<>())
                .stream().flatMap(Collection::stream).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(workList)) {
            return map;
        }

        long start = System.nanoTime();

        // sort the normal list by score, so that we don't need to sort the item list for each user
        workList.sort((s1, s2) -> s2.getScore().compareTo(s1.getScore()));

        Executor executor = TaskPool.getExecutor();
        return predictTask(param, workList, executor)
                .whenCompleteAsync((predictMap, e) -> {
                    log.info("Took [{}] to build the predict map with [{}] items"
                            , (System.nanoTime() - start) / 1_000_000
                            , CollectionUtils.isEmpty(predictMap) ? 0 : predictMap.size());
                    if (Objects.nonNull(e)) {
                        log.error("Got error:[{}]", e);
                    }
                }, executor).join();
    }

    protected CompletableFuture<ConcurrentHashMap<String, List<String>>> predictTask(Param param, List<ScoreModel> workList, Executor executor) {
        return CompletableFuture.supplyAsync(() -> buildItemsIdx(workList), executor)
                .thenApplyAsync(itemsIdx -> buildPredictMap(param, itemsIdx, workList), executor)
                .thenApplyAsync(pair -> CompletableFuture.supplyAsync(() -> normalizeMatrices(param, pair.getKey()), executor)
                        .thenApplyAsync(matrices -> buildSimilarityMap(param, matrices), executor)
                        .thenCombineAsync(CompletableFuture.supplyAsync(pair::getValue, executor)
                                , (scaleMap, itemsToUser) -> buildItemToUsersMap(param, scaleMap, itemsToUser)))
                .join();
    }

    /**
     * build recommend map for each user, based on users' similarity map and item idx map
     *
     * @param param
     * @param scaleMap
     * @param userItems
     * @return
     */
    protected ConcurrentHashMap<String, List<String>> buildItemToUsersMap(Param param
            , ConcurrentHashMap<String, List<MutablePair<String, Double>>> scaleMap
            , ConcurrentHashMap<String, List<String>> userItems) {
        long start = System.nanoTime();

        int topN = topNItem().apply(param);
        boolean selfDistinct = selfDistinct().apply(param);
        int workParallelism = workParallel().apply(param);

        ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();
        ForkJoinPool forkJoinPool = new ForkJoinPool(workParallelism);
        forkJoinPool.submit(() ->
                scaleMap.entrySet().parallelStream().forEach(e -> {
                    String id = e.getKey();
                    List<MutablePair<String, Double>> similarToId = e.getValue();

                    log.debug("Sim info: id:[{}], others:\n{}"
                            , id
                            , similarToId.stream()
                                    .map(pair -> String.format("\t%s-%s", pair.getKey(), pair.getValue()))
                                    .collect(Collectors.joining("\n")));

                    Set<String> currItems = new HashSet<>(userItems.get(id));
                    List<Double> scaledScores = similarToId.stream().map(MutablePair::getRight).collect(Collectors.toList());
                    MutablePair<Integer, Double> sumCount = CommonFuncUtils.sumCount(scaledScores);
                    double sum = 0 == sumCount.getValue() ? 1 : sumCount.getValue();

                    Set<String> distinct = ConcurrentHashMap.newKeySet();
                    List<String> recommendList = new ArrayList<>();
                    similarToId.forEach(pair -> {
                        String similarId = pair.getKey();
                        Double similarScore = pair.getValue();
                        int limit = Double.valueOf(topN * (similarScore / sum)).intValue();
                        Optional.ofNullable(userItems.get(similarId))
                                .ifPresent(itemForIdList ->
                                        itemForIdList.stream()
                                                .filter(distinct::add)
                                                .filter(item -> !selfDistinct || currItems.add(item))
                                                .limit(limit)
                                                .forEach(recommendList::add));
                    });
                    map.put(id, recommendList);
                })
        ).join();
        log.info("Took [{}] to build a [{}] itemsToUserMap", (System.nanoTime() - start) / 1_000_000, map.size());
        return map;
    }

    protected ConcurrentHashMap<String, List<MutablePair<String, Double>>> buildScaleMap(Param param
            , ConcurrentHashMap<String, List<MutablePair<String, Double>>> scoresMapping) {
        long start = System.nanoTime();

        BiFunction<Double, double[], double[]> normalizationFormula = normalizationCalc().apply(param);
        Double normalizationRange = normalizationRange().apply(param);
        int workParallelism = workParallel().apply(param);

        ConcurrentHashMap<String, List<MutablePair<String, Double>>> map = new ConcurrentHashMap<>();
        ForkJoinPool forkJoinPool = new ForkJoinPool(workParallelism);
        forkJoinPool.submit(() ->
                scoresMapping.entrySet().parallelStream()
                        .forEach(e -> {
                            List<MutablePair<String, Double>> similarityMappingList = e.getValue();
                            int size = similarityMappingList.size();
                            List<MutablePair<String, Double>> rescaledMappingList = new ArrayList<>(size);
                            List<Double> simScore = similarityMappingList.stream().map(MutablePair::getRight).collect(Collectors.toList());
                            double[] scoreArr = CommonFuncUtils.doubleListToArray(simScore);
                            double[] rescaleScoreArr = normalizationFormula.apply(normalizationRange, scoreArr);

                            int idx = 0;
                            DataUtils.forEach(idx, similarityMappingList, (i, pair) -> rescaledMappingList.add(MutablePair.of(pair.getKey(), rescaleScoreArr[i])));
                            map.put(e.getKey(), rescaledMappingList);
                        })
        ).join();
        log.info("Took [{}] to build a [{}] scaleMap", (System.nanoTime() - start) / 1_000_000, map.size());
        return map;
    }

    /**
     * build the similarity map among users' matrices
     *
     * @param param
     * @param matrices
     * @return
     */
    protected ConcurrentHashMap<String, List<MutablePair<String, Double>>> buildSimilarityMap(Param param
            , ConcurrentHashMap<String, double[]> matrices) {
        long start = System.nanoTime();
        int topSimilarity = topNSimilar().apply(param);
        BiFunction<double[], double[], Double> similarityCalc = similarityCalc().apply(param);
        int workParallelism = workParallel().apply(param);

        ConcurrentHashMap<String, List<MutablePair<String, Double>>> map = new ConcurrentHashMap<>();
        ForkJoinPool forkJoinPool = new ForkJoinPool(workParallelism);
        forkJoinPool.submit(() ->
                matrices.entrySet().parallelStream().forEach(e -> {
                    String id = e.getKey();
                    double[] matrix = e.getValue();
                    List<MutablePair<String, Double>> list = matrices.entrySet().stream()
                            .filter(se -> !id.equalsIgnoreCase(se.getKey()))
                            .map(se -> MutablePair.of(se.getKey(), similarityCalc.apply(matrix, se.getValue())))
                            .sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue()))
                            .limit(topSimilarity)
                            .collect(Collectors.toList());
                    log.debug("Most similar vector for id: [{}]:\n [{}]", id, list.stream().map(MutablePair::getLeft).collect(Collectors.joining(",")));
                    map.put(id, list);
                })).join();
        log.info("Took [{}] to build a [{}] similarityMap", (System.nanoTime() - start) / 1_000_000, map.size());
        return map;
    }

    /**
     * normalize the matrices, in case user scores too discrete
     *
     * @param param
     * @param oriMatrices
     * @return
     */
    protected ConcurrentHashMap<String, double[]> normalizeMatrices(Param param, ConcurrentHashMap<String, double[]> oriMatrices) {
        long start = System.nanoTime();
        BiFunction<Double, double[], double[]> normalizationFormula = normalizationCalc().apply(param);
        Double normalizationRange = normalizationRange().apply(param);
        ConcurrentHashMap<String, double[]> map = new ConcurrentHashMap<>(oriMatrices.size());
        oriMatrices.entrySet().parallelStream()
                .forEach(e -> map.put(e.getKey(), normalizationFormula.apply(normalizationRange, e.getValue())));
        log.info("Took [{}] to normalize matrices", (System.nanoTime() - start) / 1_000_000);

        return map;
    }

    /**
     * build item idx mapping based on normal data
     *
     * @param normalData
     * @return
     */
    protected ConcurrentHashMap<String, Integer> buildItemsIdx(List<ScoreModel> normalData) {
        long start = System.nanoTime();
        AtomicInteger counter = new AtomicInteger(0);
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        Set<String> itemsSet = new HashSet<>();
        normalData.stream()
                .map(ScoreModel::getItemId)
                .filter(itemsSet::add)
                .forEach(item -> map.put(item, counter.getAndIncrement()));
        log.info("Took [{}] to build a [{}] itemIdxMap", (System.nanoTime() - start) / 1_000_000, map.size());
        return map;
    }

    /**
     * build userMatrices and item against user map
     *
     * @param param
     * @param itemsIdx
     * @param normalData
     * @return
     */
    protected MutablePair<ConcurrentHashMap<String, double[]>, ConcurrentHashMap<String, List<String>>> buildPredictMap(Param param
            , ConcurrentHashMap<String, Integer> itemsIdx, List<ScoreModel> normalData) {
        long start = System.nanoTime();

        int topN = topNItem().apply(param);
        int itemsCount = itemsIdx.size();
        ConcurrentHashMap<String, double[]> userMatrices = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, List<String>> userItems = new ConcurrentHashMap<>();

        normalData.stream()
                .filter(scoreModel -> itemsIdx.containsKey(scoreModel.getItemId()))
                .peek(scoreModel -> {
                    String userId = scoreModel.getUserId();
                    String itemId = scoreModel.getItemId();
                    Integer itemIdx = itemsIdx.get(itemId);
                    Double score = scoreModel.getScore();

                    userMatrices.putIfAbsent(userId, new double[itemsCount]);
                    userMatrices.get(userId)[itemIdx] = score;
                })
                .forEach(scoreModel -> {
                    String userId = scoreModel.getUserId();
                    String itemId = scoreModel.getItemId();
                    userItems.putIfAbsent(userId, new ArrayList<>());
                    List<String> items = userItems.get(userId);
                    if (topN > items.size()) {
                        items.add(itemId);
                    }
                });

        log.info("Took [{}] to build a [{}] userMetricsMap and a [{}] userItemsMap", (System.nanoTime() - start) / 1_000_000, userMatrices.size(), userItems.size());
        return MutablePair.of(userMatrices, userItems);
    }

    @Override
    public BaseEnum algorithm() {
        return AlgorithmEnum.ALS;
    }

}
