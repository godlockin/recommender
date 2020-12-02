package com.st.recommender.service.als.singleAnchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.BaseEnum;
import com.st.recommender.service.DataProcessor;
import com.st.recommender.service.als.AlsPredictService;
import com.st.recommender.service.als.AlsPredictServiceImpl;
import com.st.recommender.service.als.anchorToLeafCategory.AtLCAlsPredictServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SAtLCAlsPredictServiceImpl extends AtLCAlsPredictServiceImpl implements AlsPredictService, DataProcessor {

    @Override
    public BaseEnum algorithm() { return AlgorithmEnum.SINGLE_ANCHOR_TO_LEAF_CATEGORY; }

}
