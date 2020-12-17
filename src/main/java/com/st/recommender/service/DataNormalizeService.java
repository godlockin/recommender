package com.st.recommender.service;

import com.st.recommender.model.input.Param;

import java.util.List;

public interface DataNormalizeService<T, R> extends DataProcessor {
    <T, R> R normalize(Param param, T t);
}
