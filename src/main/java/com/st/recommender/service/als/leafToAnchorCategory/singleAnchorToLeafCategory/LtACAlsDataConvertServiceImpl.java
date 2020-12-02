package com.st.recommender.service.als.leafToAnchorCategory.singleAnchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.service.als.anchorToLeafCategory.AtLCAlsDataConvertServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LtACAlsDataConvertServiceImpl extends AtLCAlsDataConvertServiceImpl {

    @Override
    public AlgorithmEnum algorithm() { return AlgorithmEnum.LEAF_TO_ANCHOR_CATEGORY; }

}