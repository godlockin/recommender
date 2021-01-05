package com.st.recommender.recommenders.als.singleAnchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.recommenders.als.anchorToLeafCategory.AtLCAlsDataConvertServiceImpl;
import com.st.recommender.service.DataConvertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SAtLCAlsDataConvertServiceImpl extends AtLCAlsDataConvertServiceImpl implements DataConvertService<Map<Class, List>, List> {

    @Override
    public AlgorithmEnum algorithm() {
        return AlgorithmEnum.SINGLE_ANCHOR_TO_LEAF_CATEGORY;
    }

}
