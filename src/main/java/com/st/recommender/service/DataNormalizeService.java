package com.st.recommender.service;

import com.st.recommender.model.input.Param;

import java.util.List;

public interface DataNormalizeService<T, R> extends DataProcessor {
    List<R> normalize(Param param, List<T> list);
}
