package com.st.recommender.service.als.singleAnchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.model.opt.als.AnchorLeaf;
import com.st.recommender.service.DataLoadService;
import com.st.recommender.service.als.anchorToLeafCategory.AtLCAlsDataLoadServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
public class SAtLCAlsDataLoadServiceImpl extends AtLCAlsDataLoadServiceImpl implements DataLoadService {

    @Value("${SATLC_ALS_INPUT_ANCHOR_PATH_KEY:anchor}")
    private String INPUT_ANCHOR_PATH_KEY;
    @Value("${SATLC_ALS_INPUT_LIVE_PATH_KEY:live}")
    private String INPUT_LIVE_PATH_KEY;
    @Value("${SATLC_ALS_INPUT_PRODUCT_PATH_KEY:product}")
    private String INPUT_PRODUCT_PATH_KEY;
    @Value("${SATLC_ALS_INPUT_COMBINE_PATH_KEY:anchorLeaf}")
    private String INPUT_ANCHOR_LEAF_PATH_KEY;

    @Override
    public AlgorithmEnum algorithm() { return AlgorithmEnum.SINGLE_ANCHOR_TO_LEAF_CATEGORY; }

    protected String getInputAnchorLeafPathKey() { return INPUT_ANCHOR_LEAF_PATH_KEY; }

    protected String getInputProductPathKey() { return INPUT_PRODUCT_PATH_KEY; }

    protected String getInputAnchorPathKey() { return INPUT_ANCHOR_PATH_KEY; }

    protected String getInputLivePathKey() { return INPUT_LIVE_PATH_KEY; }

    @Override
    protected Function<Map<String, String>, Object> dataConverter() {
        return map -> AnchorLeaf.builder()
                .anchorID(map.getOrDefault("anchor_id", ""))
                .leafCategory(map.getOrDefault("tao_leaf_category_id", ""))
                .count(Optional.ofNullable(map.get("after_speak_sale"))
                        .filter(str -> StringUtils.isNoneBlank(str) && !"\"\"".equalsIgnoreCase(str))
                        .flatMap(str -> Optional.of(Long.parseLong(str))).orElse(1L))
                .build();
    }
}