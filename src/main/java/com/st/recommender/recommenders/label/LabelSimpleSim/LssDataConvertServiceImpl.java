package com.st.recommender.recommenders.label.LabelSimpleSim;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.st.recommender.common.utils.DataUtils;
import com.st.recommender.common.utils.TaskPool;
import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.model.opt.labelbase.SeedProfile;
import com.st.recommender.model.opt.labelbase.ItemSingleLabelsMatrix;
import com.st.recommender.model.opt.labelbase.LabelGenerator;
import com.st.recommender.model.origin.AnchorLeafRaw;
import com.st.recommender.service.DataConvertService;
import com.st.recommender.service.abstractgroup.AbstractDataConvertServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LssDataConvertServiceImpl extends AbstractDataConvertServiceImpl<Map<Class, List>, Map<Class, List>> implements DataConvertService<Map<Class, List>, Map<Class, List>> {

    @Override
    protected Function<Map<Class, List>, Map<Class, List>> dataConverter() {
        return map -> {
            List<AnchorLeafRaw> anchorLeaves = map.get(AnchorLeafRaw.class);
            JSONArray groupProps = (JSONArray) map.get(LabelGenerator.class);
            if (DataUtils.isAllCollectionsEmpty(groupProps, anchorLeaves)) {
                return new HashMap<>();
            }

            Executor executor = TaskPool.getExecutor();
            long start = System.nanoTime();
            return buildItemMatricesTask(groupProps, executor)
                    .thenCombineAsync(buildAnchorProfileTask(anchorLeaves, executor)
                            , (itemMatricesList, anchorProfiles) -> {
                                Map<Class, List> tmp = new HashMap<>();
                                tmp.put(ItemSingleLabelsMatrix.class, itemMatricesList);
                                tmp.put(SeedProfile.class, anchorProfiles);
                                return tmp;
                            })
                    .whenCompleteAsync((tmp, e) -> log.info("Took {} to build data", (System.nanoTime() - start) / 1_000_000))
                    .join();
        };
    }

    private CompletableFuture<List<SeedProfile>> buildAnchorProfileTask(List<AnchorLeafRaw> anchorLeaves, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            ConcurrentHashMap<String, List<MutablePair<Long, Long>>> anchorGroup = new ConcurrentHashMap<>();
            anchorLeaves.parallelStream()
                    .filter(leaf -> StringUtils.isNoneBlank(leaf.getAnchorID(), leaf.getLeafCategory()) && 0L < leaf.getCount())
                    .forEach(leaf -> {
                String id = leaf.getAnchorID();
                anchorGroup.putIfAbsent(id, Collections.synchronizedList(new ArrayList<>()));
                Long itemId = Long.parseLong(leaf.getLeafCategory());
                anchorGroup.get(id).add(MutablePair.of(itemId, leaf.getCount()));
            });

            return anchorGroup.entrySet().parallelStream().map(e -> SeedProfile.builder().id(e.getKey()).items(e.getValue()).build()).collect(Collectors.toList());
        }, executor);
    }

    private CompletableFuture<List<ItemSingleLabelsMatrix>> buildItemMatricesTask(JSONArray groupProps, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            List<ItemSingleLabelsMatrix> itemMatricesList = new ArrayList<>();
            List<String> banKeys = Arrays.asList("_id", "itemId");
            int lineSize = groupProps.size();

            ConcurrentHashMap<String, LabelGenerator> idx = new ConcurrentHashMap<>();
            ConcurrentHashMap<Long, Map<Integer, Integer>> itemMatrices = new ConcurrentHashMap<>(lineSize);

            AtomicInteger counter = new AtomicInteger(0);
            groupProps.stream().map(o -> (JSONObject) o)
                    .peek(json -> json.entrySet().stream()
                            .filter(e -> !banKeys.contains(e.getKey()))
                            .forEach(e -> {
                                String key = e.getKey();
                                if (!idx.containsKey(key)) {
                                    idx.put(key, new LabelGenerator(key, counter.getAndIncrement()));
                                }
                                idx.get(key).appendValue(String.valueOf(e.getValue()));
                            }))
                    .forEach(json -> {
                        Long itemId = json.getLongValue("itemId");
                        Map<Integer, Integer> itemMatrix = new HashMap<>(json.size() - 2);
                        json.entrySet().stream()
                                .filter(e -> !banKeys.contains(e.getKey()))
                                .forEach(e -> {
                                    String key = e.getKey();
                                    String value = String.valueOf(e.getValue());
                                    LabelGenerator lg = idx.get(key);
                                    Integer valueIdx = lg.getValueItemCounter().get(value).getKey();
                                    itemMatrix.put(lg.getIdx(), valueIdx);
                                });
                        itemMatrices.put(itemId, itemMatrix);
                    });

            int labelSize = idx.size();

            itemMatrices.forEach((itemId, itemMatrix) -> {
                ItemSingleLabelsMatrix ilg = new ItemSingleLabelsMatrix(itemId, labelSize);
                itemMatrix.forEach((labelId, labelCount) -> ilg.getMatrix()[labelId] = labelCount);
                itemMatricesList.add(ilg);
            });
            return itemMatricesList;
        }, executor);
    }

    @Override
    public AlgorithmEnum algorithm() {
        return AlgorithmEnum.LABEL_SIMPLE_SIM;
    }

}
