package com.st.recommender.service;

import com.st.recommender.model.input.Param;

public interface PredictService extends DataProcessor {

    String predict(Param param);
}
