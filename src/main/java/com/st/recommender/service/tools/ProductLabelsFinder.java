package com.st.recommender.service.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.st.recommender.model.opt.labelbase.ItemSingleLabelsMatrix;
import com.st.recommender.model.opt.labelbase.LabelGenerator;
import com.st.recommender.service.FileOperationService;
import com.st.recommender.service.impl.FileOperationServiceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductLabelsFinder {
    private static FileOperationService service = new FileOperationServiceImpl();

    public static void main(String[] args) {
        String productInputFilePath = "/Users/stevenchen/working/doc/recommendation_system/example_groupProps商品规格属性样本数据/part-00000-3024fcf2-7a5d-404a-93c7-c23e34047f73-c000.json";
        List<String> banKeys = Arrays.asList("_id", "itemId");

        JSONArray lines = service.loadFileAsCamelJson(productInputFilePath);
        int lineSize = lines.size();

        ConcurrentHashMap<String, LabelGenerator> idx = new ConcurrentHashMap<>();
        ConcurrentHashMap<Long, Map<Integer, Integer>> itemMatrices = new ConcurrentHashMap<>(lineSize);

        AtomicInteger counter = new AtomicInteger(0);
        lines.stream().map(o -> (JSONObject) o)
                .peek(json -> json.entrySet().stream()
                        .filter(e -> !banKeys.contains(e.getKey()))
                        .forEach(e -> {
                            String key = e.getKey();
                            if (!idx.containsKey(key)) {
                                idx.put(key, new LabelGenerator(key, counter.incrementAndGet()));
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
        });

    }
}
