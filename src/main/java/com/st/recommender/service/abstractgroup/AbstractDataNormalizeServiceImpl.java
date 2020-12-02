package com.st.recommender.service.abstractgroup;

import com.st.recommender.model.input.Param;
import com.st.recommender.service.DataNormalizeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public abstract class AbstractDataNormalizeServiceImpl<T, R> extends AbstractDataProcessorImpl implements DataNormalizeService<T, R> {

    public List<R> normalize(Param param, List<T> list) {
        return (List<R>) dataNormalizer().apply(param, (T) list);
    }

    protected abstract BiFunction<Param, T, R> dataNormalizer();

}
