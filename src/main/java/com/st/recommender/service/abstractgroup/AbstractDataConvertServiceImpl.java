package com.st.recommender.service.abstractgroup;

import com.st.recommender.service.DataConvertService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public abstract class AbstractDataConvertServiceImpl<T, R> extends AbstractDataProcessorImpl implements DataConvertService<T, R> {

    public <R> R convert(T data) { return (R) dataConverter().apply(data); }

    protected abstract Function<T, R> dataConverter();

}
