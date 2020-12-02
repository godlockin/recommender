package com.st.recommender.service.als.singleAnchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.service.als.anchorToLeafCategory.AtLCAlsDataNormalizeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SAtLCAlsDataNormalizeServiceImpl extends AtLCAlsDataNormalizeServiceImpl {

    @Override
    public AlgorithmEnum algorithm() { return AlgorithmEnum.SINGLE_ANCHOR_TO_LEAF_CATEGORY; }

}
