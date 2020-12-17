package com.st.recommender.recommenders.label.LabelSimpleSim;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.DataLoadTypeEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.model.opt.CsvFileLoadConfig;
import com.st.recommender.model.origin.AnchorLeafRaw;
import com.st.recommender.model.opt.labelbase.LabelGenerator;
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
public class LssDataLoadServiceImpl extends AbstractDataLoadServiceImpl implements DataLoadService {

    @Value("${LSS_INPUT_PRODUCT_PATH_KEY:product}")
    private String INPUT_PRODUCT_PATH_KEY;
    @Value("${LSS_INPUT_COMBINE_PATH_KEY:anchorLeaf}")
    private String INPUT_ANCHOR_LEAF_PATH_KEY;

    @Override
    public AlgorithmEnum algorithm() {
        return AlgorithmEnum.LABEL_SIMPLE_SIM;
    }

    protected String getInputAnchorLeafPathKey() { return INPUT_ANCHOR_LEAF_PATH_KEY; }

    protected String getInputProductPathKey() {
        return INPUT_PRODUCT_PATH_KEY;
    }

    @Override
    protected Map<String, Class> pojoClass() {
        Map<String, Class> map = new HashMap<>();
        map.put(getInputProductPathKey(), LabelGenerator.class);
        map.put(getInputAnchorLeafPathKey(), AnchorLeafRaw.class);
        return map;
    }

    @Override
    protected Map<String, DataLoadTypeEnum> dataLoadTypeFinder(Param param) {
        Map<String, DataLoadTypeEnum> map = new HashMap<>();
        map.put(getInputAnchorLeafPathKey(), DataLoadTypeEnum.CSV);
        map.put(getInputProductPathKey(), DataLoadTypeEnum.JSON_JSON);
        return map;
    }

    @Override
    protected Map<String, CsvFileLoadConfig> csvFileLoadConfigBuilder(Param param) {
        Map<String, CsvFileLoadConfig> configMap = new HashMap<>();
        CsvFileLoadConfig config = CsvFileLoadConfig.builder()
                .clazz(AnchorLeafRaw.class)
                .delimiter(delimiterFinder(param))
                .dataConverter(dataConverter())
                .dataJudge(dataJudge())
                .header(headerFinder(param))
                .build();

        configMap.put(getInputAnchorLeafPathKey(), config);
        return configMap;
    }

    protected List<String> headerFinder(Param param) {
        return Arrays.asList("anchor_id"
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
        return map -> AnchorLeafRaw.builder()
                .anchorID(map.getOrDefault("anchor_id", ""))
                .leafCategory(map.getOrDefault("tao_leaf_category_id", ""))
                .count(Optional.ofNullable(map.get("after_speak_sale"))
                        .filter(str -> StringUtils.isNoneBlank(str) && !"\"\"".equalsIgnoreCase(str))
                        .flatMap(str -> Optional.of(Long.parseLong(str))).orElse(1L))
                .build();
    }

    @Override
    protected Predicate<Object> dataJudge() {
        return o -> {
            AnchorLeafRaw anchorLeafRaw = (AnchorLeafRaw) o;
            return StringUtils.isNoneBlank(anchorLeafRaw.getAnchorID(), anchorLeafRaw.getLeafCategory()) &&
                    0L < Optional.ofNullable(anchorLeafRaw.getCount()).orElse(0L);
        };
    }
}