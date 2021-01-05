package com.st.recommender.recommenders.als.leafToAnchorCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.recommenders.als.anchorToLeafCategory.AtLCAlsDataConvertServiceImpl;
import com.st.recommender.service.DataConvertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class LtACAlsDataConvertServiceImpl extends AtLCAlsDataConvertServiceImpl implements DataConvertService<Map<Class, List>, List> {

    @Override
    public AlgorithmEnum algorithm() {
        return AlgorithmEnum.LEAF_TO_ANCHOR_CATEGORY;
    }

}
