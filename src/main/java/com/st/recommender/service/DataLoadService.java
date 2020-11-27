package com.st.recommender.service;

import com.st.recommender.model.input.Param;

import java.util.List;
import java.util.Map;

public interface DataLoadService extends DataProcessor {
    Map<Class, List> loadData(Param param);
}
