package com.st.recommender.service.abstractgroup;

import com.st.recommender.service.DataConvertService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public abstract class AbstractDataConvertServiceImpl<T, R> extends AbstractDataProcessorImpl implements DataConvertService<T, R> {

    public List<R> convert(List<T> list) {
        return list.stream().map(dataConverter()).collect(Collectors.toList());
    }

    protected abstract Function<T, R> dataConverter();

}
