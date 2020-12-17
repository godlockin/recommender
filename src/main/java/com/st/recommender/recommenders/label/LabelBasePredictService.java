package com.st.recommender.recommenders.label;

import com.st.recommender.model.input.Param;
import com.st.recommender.service.DataProcessor;
import com.st.recommender.service.PredictService;

import java.util.List;
import java.util.Map;

public interface LabelBasePredictService extends PredictService, DataProcessor {
    Map<String, List<String>> doPredict(Param param, Map<Class, List> normalData);
}
