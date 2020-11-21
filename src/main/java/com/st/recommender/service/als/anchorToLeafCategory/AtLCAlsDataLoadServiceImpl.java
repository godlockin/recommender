package com.st.recommender.service.als.anchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.model.origin.Anchor;
import com.st.recommender.service.DataLoadService;
import com.st.recommender.service.abstractgroup.AbstractDataLoadServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Slf4j
@Service
public class AtLCAlsDataLoadServiceImpl extends AbstractDataLoadServiceImpl implements DataLoadService {

    @Override
    protected Class<Anchor> pojoClass() { return Anchor.class; }

    @Override
    protected Function<Param, String> filePathConverter() {
        return Param::getInputFilePath;
    }

    @Override
    public AlgorithmEnum algorithm() { return AlgorithmEnum.ANCHOR_TO_LEAF_CATEGORY; }
}