package com.st.recommender.service.als.anchorToLeafCategory;

import com.st.recommender.common.utils.TaskPool;
import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.BaseEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.model.opt.als.ScoreModel;
import com.st.recommender.service.DataProcessor;
import com.st.recommender.service.als.AlsPredictService;
import com.st.recommender.service.als.AlsPredictServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AtLCAlsPredictServiceImpl extends AlsPredictServiceImpl implements AlsPredictService, DataProcessor {

    @Override
    public Map<String, List<String>> doPredict(Param Param, List<List<ScoreModel>> normalData) {
        int topN = Optional.ofNullable(Param.getTopN()).orElse(100);
        int topSimilarity = Optional.ofNullable(Param.getTopSimilarity()).orElse(20);
        Map<String, List<String>> map = new HashMap<>();
        List<ScoreModel> workList = Optional.ofNullable(normalData).orElse(new ArrayList<>())
                .stream().flatMap(Collection::stream).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(workList)) {
            return map;
        }

        workList.sort((s1, s2) -> s2.getScore().compareTo(s1.getScore()));

        Executor executor = TaskPool.getExecutor();
        return CompletableFuture.supplyAsync(() -> buildItemsIdx(workList), executor)
                .thenApplyAsync(itemsIdx -> buildPredictMap(topN, itemsIdx, workList), executor)
                .thenApplyAsync(pair -> CompletableFuture.supplyAsync(pair::getKey, executor)
                        .thenApplyAsync(metrics -> buildSimilarityMap(topSimilarity, metrics), executor)
                        .thenApplyAsync(similarityMap -> buildScaleMap(topN, similarityMap), executor)
                        .thenCombineAsync(CompletableFuture.supplyAsync(pair::getValue, executor), this::buildItemToUsersMap))
                .join()
                .join();
    }

    private ConcurrentHashMap<String, List<String>> buildItemToUsersMap(ConcurrentHashMap<String, List<MutablePair<String, Double>>> scaleMap
            , ConcurrentHashMap<String, List<String>> items) {
        long start = System.nanoTime();
        ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();
        ForkJoinPool forkJoinPool = new ForkJoinPool(20);
        forkJoinPool.submit(() ->
                scaleMap.entrySet().parallelStream().forEach(e -> {
                    String userId = e.getKey();
                    List<MutablePair<String, Double>> scaleToUser = e.getValue();
                    Set<String> distinct = ConcurrentHashMap.newKeySet();
                    List<String> list = scaleToUser.stream().map(pair -> {
                        String similarUserId = pair.getLeft();
                        List<String> userItems = items.get(similarUserId);
                        return userItems.stream().filter(distinct::add).limit(pair.getRight().intValue()).collect(Collectors.toList());
                    }).flatMap(Collection::stream).collect(Collectors.toList());
                    map.put(userId, list);
                })
        ).join();
        log.info("Took [{}] to build a [{}] itemsToUserMap", (System.nanoTime() - start) / 1_000_000, map.size());
        return map;
    }

    private Double buildRescaleScore(Double rate, Double score, Double min, Double delta) { return rate * (score - min) / delta; }

    private Boolean scoreFilter(Double score) { return Objects.nonNull(score) && !(score.isNaN() || score.isInfinite()); }

    private MutablePair<Double, Double> maxMinItemFinder(List<Double> items) {
        double min = Double.MIN_VALUE;
        double max = Double.MAX_VALUE;
        for (Double item : items) {
            if (!scoreFilter(item)) continue;
            min = min > item ? item : min;
            max = max < item ? item : max;
        }
        return MutablePair.of(min, max);
    }

    private List<MutablePair<String, Double>> rescale(Double rate, List<MutablePair<String, Double>> list) {
        Double rateD = Optional.ofNullable(rate).orElse(1D);
        List<Double> items = list.stream().map(MutablePair::getRight).collect(Collectors.toList());

        MutablePair<Double, Double> minMax = maxMinItemFinder(items);
        Double min = minMax.getLeft();
        Double max = minMax.getRight();
        double delta = (max.equals(min)) ? 1 : max - min;

        return list.parallelStream().filter(pair -> scoreFilter(pair.getRight()))
                .map(pair -> MutablePair.of(pair.getLeft(), buildRescaleScore(rateD, pair.getRight(), min, delta)))
                .collect(Collectors.toList());
    }

    private ConcurrentHashMap<String, List<MutablePair<String, Double>>> buildScaleMap(int topN
            , ConcurrentHashMap<String, List<MutablePair<String, Double>>> similarityMap) {
        long start = System.nanoTime();
        ConcurrentHashMap<String, List<MutablePair<String, Double>>> map = new ConcurrentHashMap<>();
        ForkJoinPool forkJoinPool = new ForkJoinPool(20);
        forkJoinPool.submit(() ->
                similarityMap.entrySet().parallelStream()
                        .map(e -> MutablePair.of(e.getKey(), rescale((double) topN, e.getValue())))
                        .forEach(pair -> map.put(pair.getKey(), pair.getValue()))
        ).join();
        log.info("Took [{}] to build a [{}] scaleMap", (System.nanoTime() - start) / 1_000_000, map.size());
        return map;
    }

    private ConcurrentHashMap<String, List<MutablePair<String, Double>>> buildSimilarityMap(int topSimilarity
            , ConcurrentHashMap<String, double[]> metrics) {
        long start = System.nanoTime();
        ConcurrentHashMap<String, List<MutablePair<String, Double>>> map = new ConcurrentHashMap<>();
        ForkJoinPool forkJoinPool = new ForkJoinPool(20);
        forkJoinPool.submit(() ->
                metrics.entrySet().parallelStream().forEach(e -> {
                    String userId = e.getKey();
                    double[] userMetric = e.getValue();
                    List<MutablePair<String, Double>> list = metrics.entrySet().stream()
                            .filter(se -> !userId.equalsIgnoreCase(se.getKey()))
                            .map(se -> MutablePair.of(se.getKey(), similarity(userMetric, se.getValue())))
                            .sorted((p1, p2) -> p2.getValue().compareTo(p1.getValue()))
                            .limit(topSimilarity)
                            .collect(Collectors.toList());
                    map.put(userId, list);
                })).join();
        log.info("Took [{}] to build a [{}] similarityMap", (System.nanoTime() - start) / 1_000_000, map.size());
        return map;
    }

    private double similarity(double[] vector1, double[] vector2) {
        if (vector1.length != vector2.length) {
            return 0D;
        }

        double sum = 0D;
        int size = vector1.length;
        for (int i = 0; i < size; i ++) {
            sum += Math.pow((vector1[i] - vector2[i]), 2);
        }

        return Math.sqrt(sum);
    }

    private ConcurrentHashMap<String, Integer> buildItemsIdx(List<ScoreModel> normalData) {
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

    private MutablePair<ConcurrentHashMap<String, double[]>, ConcurrentHashMap<String, List<String>>> buildPredictMap(int topN
            , ConcurrentHashMap<String, Integer> itemsIdx, List<ScoreModel> normalData) {
        long start = System.nanoTime();

        int itemsCount = itemsIdx.size();
        ConcurrentHashMap<String, double[]> userMetrics = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, List<String>> userItems = new ConcurrentHashMap<>();

        normalData.stream()
                .filter(scoreModel -> itemsIdx.containsKey(scoreModel.getItemId()))
                .peek(scoreModel -> {
                    String userId = scoreModel.getUserId();
                    String itemId = scoreModel.getItemId();
                    Integer itemIdx = itemsIdx.get(itemId);
                    Double score = scoreModel.getScore();

                    userMetrics.putIfAbsent(userId, new double[itemsCount]);
                    userMetrics.get(userId)[itemIdx] = score;
                }).forEach(scoreModel -> {
            String userId = scoreModel.getUserId();
            String itemId = scoreModel.getItemId();
            userItems.putIfAbsent(userId, new ArrayList<>());
            List<String> items = userItems.get(userId);
            if (topN < items.size()) {
                items.add(itemId);
            }
        });

        log.info("Took [{}] to build a [{}] userMetricsMap and a [{}] userItemsMap", (System.nanoTime() - start) / 1_000_000, userMetrics.size(), userItems.size());
        return MutablePair.of(userMetrics, userItems);
    }

    @Override
    public BaseEnum algorithm() { return AlgorithmEnum.ANCHOR_TO_LEAF_CATEGORY; }

}
