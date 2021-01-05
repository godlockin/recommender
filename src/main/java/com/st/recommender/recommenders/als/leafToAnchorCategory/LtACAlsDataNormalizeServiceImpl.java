package com.st.recommender.recommenders.als.leafToAnchorCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.recommenders.als.anchorToLeafCategory.AtLCAlsDataNormalizeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LtACAlsDataNormalizeServiceImpl extends AtLCAlsDataNormalizeServiceImpl {

    @Override
    public AlgorithmEnum algorithm() {
        return AlgorithmEnum.LEAF_TO_ANCHOR_CATEGORY;
    }

}
