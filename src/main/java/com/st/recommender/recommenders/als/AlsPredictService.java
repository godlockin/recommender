package com.st.recommender.recommenders.als;

import com.st.recommender.model.input.Param;
import com.st.recommender.model.opt.als.ScoreModel;
import com.st.recommender.service.DataProcessor;
import com.st.recommender.service.PredictService;

import java.util.List;
import java.util.Map;

public interface AlsPredictService extends PredictService, DataProcessor {
    Map<String, List<String>> doPredict(Param Param, List<List<ScoreModel>> normalData);
}
