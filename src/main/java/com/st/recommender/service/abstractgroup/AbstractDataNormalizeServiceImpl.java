package com.st.recommender.service.abstractgroup;

import com.st.recommender.model.input.Param;
import com.st.recommender.service.DataNormalizeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
public abstract class AbstractDataNormalizeServiceImpl<T, R> extends AbstractDataProcessorImpl implements DataNormalizeService<T, R> {

    public <T, R> R normalize(Param param, T t) {
        return (R) dataNormalizer().apply(param, t);
    }

    protected abstract BiFunction<Param, Object, R> dataNormalizer();

}
