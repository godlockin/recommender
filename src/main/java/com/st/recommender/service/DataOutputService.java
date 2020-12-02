package com.st.recommender.service;

import com.st.recommender.model.input.Param;

public interface DataOutputService extends DataProcessor {
    <T> String outputAsFile(Param param, T data);
}
