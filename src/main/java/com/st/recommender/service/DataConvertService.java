package com.st.recommender.service;

import java.util.List;

public interface DataConvertService<T, R> extends DataProcessor {
    List<R> convert(List<T> list);
}
