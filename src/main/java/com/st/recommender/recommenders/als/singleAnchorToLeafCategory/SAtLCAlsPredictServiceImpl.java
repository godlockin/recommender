package com.st.recommender.recommenders.als.singleAnchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.BaseEnum;
import com.st.recommender.recommenders.als.AlsPredictService;
import com.st.recommender.recommenders.als.anchorToLeafCategory.AtLCAlsPredictServiceImpl;
import com.st.recommender.service.DataProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SAtLCAlsPredictServiceImpl extends AtLCAlsPredictServiceImpl implements AlsPredictService, DataProcessor {

    @Override
    public BaseEnum algorithm() {
        return AlgorithmEnum.SINGLE_ANCHOR_TO_LEAF_CATEGORY;
    }

}
