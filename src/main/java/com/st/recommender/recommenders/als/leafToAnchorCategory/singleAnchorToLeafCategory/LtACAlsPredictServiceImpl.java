package com.st.recommender.recommenders.als.leafToAnchorCategory.singleAnchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.BaseEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.model.opt.als.ScoreModel;
import com.st.recommender.recommenders.als.AlsPredictService;
import com.st.recommender.recommenders.als.anchorToLeafCategory.AtLCAlsPredictServiceImpl;
import com.st.recommender.service.DataProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class LtACAlsPredictServiceImpl extends AtLCAlsPredictServiceImpl implements AlsPredictService, DataProcessor {

    @Override
    public BaseEnum algorithm() {
        return AlgorithmEnum.LEAF_TO_ANCHOR_CATEGORY;
    }

    protected CompletableFuture<ConcurrentHashMap<String, List<String>>> predictTask(Param param, List<ScoreModel> workList, Executor executor) {
        return CompletableFuture.supplyAsync(() -> buildItemsIdx(workList), executor)
                .thenApplyAsync(itemsIdx -> buildLtACPredictMap(param, itemsIdx, workList), executor)
                .thenApplyAsync(pair -> CompletableFuture.supplyAsync(() -> normalizeMatrices(param, pair.getKey()), executor)
                        .thenApplyAsync(matrices -> buildSimilarityMap(param, matrices), executor)
                        .thenCombineAsync(CompletableFuture.supplyAsync(pair::getValue, executor)
                                , (scaleMap, itemsToUser) -> buildLtACItemToUsersMap(param, scaleMap, itemsToUser)))
                .join();
    }

    /**
     * build user idx mapping based on normal data
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
                .map(ScoreModel::getUserId)
                .filter(itemsSet::add)
                .forEach(item -> map.put(item, counter.getAndIncrement()));
        log.info("Took [{}] to build a [{}] userIdxMap", (System.nanoTime() - start) / 1_000_000, map.size());
        return map;
    }

    /**
     * build itemMatrices and item against user map
     *
     * @param param
     * @param userIdxMap
     * @param normalData
     * @return
     */
    protected MutablePair<ConcurrentHashMap<String, double[]>, ConcurrentHashMap<String, List<MutablePair<String, Double>>>> buildLtACPredictMap(Param param
            , ConcurrentHashMap<String, Integer> userIdxMap, List<ScoreModel> normalData) {
        long start = System.nanoTime();

        int topN = topNItem().apply(param);
        int userCount = userIdxMap.size();
        ConcurrentHashMap<String, double[]> itemMatrices = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, List<MutablePair<String, Double>>> userItems = new ConcurrentHashMap<>();

        normalData.stream()
                .filter(scoreModel -> userIdxMap.containsKey(scoreModel.getUserId()))
                .peek(scoreModel -> {
                    String userId = scoreModel.getUserId();
                    String itemId = scoreModel.getItemId();
                    Integer userIdx = userIdxMap.get(userId);
                    Double score = scoreModel.getScore();

                    itemMatrices.putIfAbsent(itemId, new double[userCount]);
                    itemMatrices.get(itemId)[userIdx] = score;
                })
                .forEach(scoreModel -> {
                    String userId = scoreModel.getUserId();
                    String itemId = scoreModel.getItemId();
                    Double score = scoreModel.getScore();
                    userItems.putIfAbsent(userId, new ArrayList<>());
                    List<MutablePair<String, Double>> items = userItems.get(userId);
                    if (topN > items.size()) {
                        items.add(MutablePair.of(itemId, score));
                    }
                });

        log.info("Took [{}] to build a [{}] userMetricsMap and a [{}] userItemsMap", (System.nanoTime() - start) / 1_000_000, itemMatrices.size(), userItems.size());
        return MutablePair.of(itemMatrices, userItems);
    }

    /**
     * build recommend map for each user, based on users' similarity map and item idx map
     *
     * @param param
     * @param scaleMap
     * @param userItems
     * @return
     */
    private ConcurrentHashMap<String, List<String>> buildLtACItemToUsersMap(Param param
            , ConcurrentHashMap<String, List<MutablePair<String, Double>>> scaleMap
            , ConcurrentHashMap<String, List<MutablePair<String, Double>>> userItems) {
        long start = System.nanoTime();

        int topN = topNItem().apply(param);
        boolean selfDistinct = selfDistinct().apply(param);

        ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();
        buildScaleMap(param, userItems).forEach((userId, normalizedItemScores) -> {
            Set<String> currItems = ConcurrentHashMap.newKeySet();
            Set<String> resultItems = ConcurrentHashMap.newKeySet();
            normalizedItemScores.parallelStream().forEach(pair -> currItems.add(pair.getKey()));

            normalizedItemScores.forEach(itemScore -> {
                String itemId = itemScore.getKey();
                Double rate = itemScore.getValue();
                int limit = Double.valueOf(topN * rate).intValue();
                List<MutablePair<String, Double>> itemSimList = scaleMap.getOrDefault(itemId, new ArrayList<>());
                itemSimList.stream()
                        .filter(pair -> !selfDistinct || currItems.add(pair.getKey()))
                        .limit(limit)
                        .map(MutablePair::getKey)
                        .forEach(resultItems::add);
            });

            map.put(userId, new ArrayList<>(resultItems));
        });
        log.info("Took [{}] to build a [{}] itemsToUserMap", (System.nanoTime() - start) / 1_000_000, map.size());
        return map;
    }
}
