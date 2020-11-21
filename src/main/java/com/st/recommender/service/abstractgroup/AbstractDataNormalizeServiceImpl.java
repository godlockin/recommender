package com.st.recommender.service.abstractgroup;

import com.st.recommender.service.DataNormalizeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public abstract class AbstractDataNormalizeServiceImpl<T, R> extends AbstractDataProcessorImpl implements DataNormalizeService<T, R> {

    public List<R> normalize(List<T> list) {
        return list.stream().map(dataNormalizer()).collect(Collectors.toList());
    }

    protected abstract Function<T, R> dataNormalizer();

}
