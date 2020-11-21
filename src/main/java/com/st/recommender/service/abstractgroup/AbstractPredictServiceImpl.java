package com.st.recommender.service.abstractgroup;

import com.st.recommender.model.input.Param;
import com.st.recommender.service.PredictService;

public abstract class AbstractPredictServiceImpl extends AbstractDataProcessorImpl implements PredictService {
    public abstract String predict(Param param);
}
