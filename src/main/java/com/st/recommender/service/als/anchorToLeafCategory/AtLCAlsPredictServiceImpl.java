package com.st.recommender.service.als.anchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.BaseEnum;
import com.st.recommender.service.DataProcessor;
import com.st.recommender.service.als.AlsPredictService;
import com.st.recommender.service.als.AlsPredictServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AtLCAlsPredictServiceImpl extends AlsPredictServiceImpl implements AlsPredictService, DataProcessor {

    @Override
    public BaseEnum algorithm() { return AlgorithmEnum.ANCHOR_TO_LEAF_CATEGORY; }

}
