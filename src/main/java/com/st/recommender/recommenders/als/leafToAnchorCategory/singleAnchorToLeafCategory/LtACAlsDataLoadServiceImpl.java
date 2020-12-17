package com.st.recommender.recommenders.als.leafToAnchorCategory.singleAnchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.recommenders.als.singleAnchorToLeafCategory.SAtLCAlsDataLoadServiceImpl;
import com.st.recommender.service.DataLoadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LtACAlsDataLoadServiceImpl extends SAtLCAlsDataLoadServiceImpl implements DataLoadService {

    @Value("${SATLC_ALS_INPUT_ANCHOR_PATH_KEY:anchor}")
    private String INPUT_ANCHOR_PATH_KEY;
    @Value("${SATLC_ALS_INPUT_LIVE_PATH_KEY:live}")
    private String INPUT_LIVE_PATH_KEY;
    @Value("${SATLC_ALS_INPUT_PRODUCT_PATH_KEY:product}")
    private String INPUT_PRODUCT_PATH_KEY;
    @Value("${SATLC_ALS_INPUT_COMBINE_PATH_KEY:anchorLeaf}")
    private String INPUT_ANCHOR_LEAF_PATH_KEY;

    @Override
    public AlgorithmEnum algorithm() {
        return AlgorithmEnum.LEAF_TO_ANCHOR_CATEGORY;
    }

    protected String getInputAnchorLeafPathKey() {
        return INPUT_ANCHOR_LEAF_PATH_KEY;
    }

    protected String getInputProductPathKey() {
        return INPUT_PRODUCT_PATH_KEY;
    }

    protected String getInputAnchorPathKey() {
        return INPUT_ANCHOR_PATH_KEY;
    }

    protected String getInputLivePathKey() {
        return INPUT_LIVE_PATH_KEY;
    }

}