package com.st.recommender.service;

import com.st.recommender.constants.BaseEnum;

public interface DataProcessor {
    BaseEnum algorithm();

    default String processorName() {
        return getClass().getSimpleName();
    }
}
