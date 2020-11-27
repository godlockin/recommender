package com.st.recommender.service;

public interface DataConvertService<T, R> extends DataProcessor {
    <R> R convert(T data);
}
