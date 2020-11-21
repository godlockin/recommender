package com.st.recommender.service.abstractgroup;

import com.st.recommender.service.DataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class AbstractDataProcessorImpl implements DataProcessor {

    @Autowired
    protected ApplicationContext context;
}
