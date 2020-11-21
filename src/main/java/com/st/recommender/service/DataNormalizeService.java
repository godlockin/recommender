package com.st.recommender.service;

import java.util.List;

public interface DataNormalizeService<T, R> extends DataProcessor {
    List<R> normalize(List<T> list);
}
