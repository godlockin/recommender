package com.st.recommender.service;

import com.st.recommender.model.input.Param;

import java.util.List;

public interface DataLoadService extends DataProcessor {
    <T> List<T> loadData(Param param);
}
