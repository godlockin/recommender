package com.st.recommender.service.als.anchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.DataLoadTypeEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.model.opt.CsvFileLoadConfig;
import com.st.recommender.model.opt.als.AnchorLeaf;
import com.st.recommender.model.origin.Anchor;
import com.st.recommender.model.origin.Live;
import com.st.recommender.model.origin.Product;
import com.st.recommender.service.DataLoadService;
import com.st.recommender.service.abstractgroup.AbstractDataLoadServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
@Service
public class AtLCAlsDataLoadServiceImpl extends AbstractDataLoadServiceImpl implements DataLoadService {

    @Value("${ATLC_ALS_INPUT_ANCHOR_PATH_KEY:anchor}")
    private String INPUT_ANCHOR_PATH_KEY;
    @Value("${ATLC_ALS_INPUT_LIVE_PATH_KEY:live}")
    private String INPUT_LIVE_PATH_KEY;
    @Value("${ATLC_ALS_INPUT_PRODUCT_PATH_KEY:product}")
    private String INPUT_PRODUCT_PATH_KEY;
    @Value("${ATLC_ALS_INPUT_COMBINE_PATH_KEY:anchorLeaf}")
    private String INPUT_ANCHOR_LEAF_PATH_KEY;

    protected Function<Param, DataLoadTypeEnum> dataLoadTypeFinder() {
        return param -> {
            Map<String, String> inputMap = Optional.ofNullable(param.getInputFilePath()).orElse(new HashMap<>());
            if (StringUtils.isNotBlank(inputMap.getOrDefault(INPUT_ANCHOR_LEAF_PATH_KEY, ""))) {
                return DataLoadTypeEnum.CSV;
            }
            return DataLoadTypeEnum.JSON;
        };
    }

    @Override
    protected Map<String, Class> pojoClass() {
        Map<String, Class> map = new HashMap<>();
        map.put(INPUT_LIVE_PATH_KEY, Live.class);
        map.put(INPUT_ANCHOR_PATH_KEY, Anchor.class);
        map.put(INPUT_PRODUCT_PATH_KEY, Product.class);
        map.put(INPUT_ANCHOR_LEAF_PATH_KEY, AnchorLeaf.class);
        return map;
    }

    @Override
    protected Function<Param, Map<String, CsvFileLoadConfig>> csvFileLoadConfigBuilder() {
        return param -> {
            Map<String, CsvFileLoadConfig> configMap = new HashMap<>();
            String delimiter = (String) delimiterFinder().apply(param);
            CsvFileLoadConfig config = CsvFileLoadConfig.builder()
                    .clazz(AnchorLeaf.class)
                    .delimiter(delimiter)
                    .dataConverter(dataConverter())
                    .dataJudge(dataJudge())
                    .header(headerFinder().apply(param))
                    .build();

            configMap.put(INPUT_ANCHOR_LEAF_PATH_KEY, config);
            return configMap;
        };
    }

    protected Function<Param, List<String>> headerFinder() {
        return p -> Arrays.asList("anchor_id"
                , "live_id"
                , "item_id"
                , "live_price"
                , "live_duration"
                , "fans_count"
                , "pv"
                , "tao_leaf_category_id"
                , "after_speak_sale"
                , "start_time");
    }

    @Override
    protected Function<Map<String, String>, Object> dataConverter() {
        return map -> AnchorLeaf.builder()
                .anchorID(map.getOrDefault("anchor_id", ""))
                .leafCategory(map.getOrDefault("tao_leaf_category_id", ""))
                .count(Optional.ofNullable(map.get("after_speak_sale"))
                        .filter(str -> StringUtils.isNoneBlank(str) && !"\"\"".equalsIgnoreCase(str))
                        .flatMap(str -> Optional.of(Long.parseLong(str))).orElse(0L))
                .build();
    }

    @Override
    protected Predicate<Object> dataJudge() {
        return o -> {
            AnchorLeaf anchorLeaf = (AnchorLeaf) o;
            return StringUtils.isNoneBlank(anchorLeaf.getAnchorID(), anchorLeaf.getLeafCategory()) &&
                    0L < Optional.ofNullable(anchorLeaf.getCount()).orElse(0L);
        };
    }

    @Override
    protected Function<Param, Map<String, String>> filePathConverter() {
        return param -> {
            Map<String, String> inputMap = Optional.ofNullable(param.getInputFilePath()).orElse(new HashMap<>());
            Map<String, String> map = new HashMap<>();
            map.put(INPUT_LIVE_PATH_KEY, inputMap.get(INPUT_LIVE_PATH_KEY));
            map.put(INPUT_ANCHOR_PATH_KEY, inputMap.get(INPUT_ANCHOR_PATH_KEY));
            map.put(INPUT_PRODUCT_PATH_KEY, inputMap.get(INPUT_PRODUCT_PATH_KEY));
            map.put(INPUT_ANCHOR_LEAF_PATH_KEY, inputMap.get(INPUT_ANCHOR_LEAF_PATH_KEY));
            return map;
        };
    }

    @Override
    public AlgorithmEnum algorithm() { return AlgorithmEnum.ANCHOR_TO_LEAF_CATEGORY; }

}