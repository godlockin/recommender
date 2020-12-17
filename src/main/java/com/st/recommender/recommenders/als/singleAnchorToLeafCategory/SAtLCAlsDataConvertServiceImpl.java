package com.st.recommender.recommenders.als.singleAnchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.recommenders.als.anchorToLeafCategory.AtLCAlsDataConvertServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SAtLCAlsDataConvertServiceImpl extends AtLCAlsDataConvertServiceImpl {

    @Override
    public AlgorithmEnum algorithm() {
        return AlgorithmEnum.SINGLE_ANCHOR_TO_LEAF_CATEGORY;
    }

}
