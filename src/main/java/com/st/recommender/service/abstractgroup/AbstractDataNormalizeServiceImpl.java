package com.st.recommender.service.abstractgroup;

import com.st.recommender.constants.NormalizationEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.service.DataNormalizeService;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public abstract class AbstractDataNormalizeServiceImpl<T, R> extends AbstractDataProcessorImpl implements DataNormalizeService<T, R> {

    public <T, R> R normalize(Param param, T t) {
        return (R) dataNormalizer().apply(param, t);
    }

    protected abstract BiFunction<Param, Object, R> dataNormalizer();

    protected Function<Param, NormalizationEnum> findNormalizationFunc() {
        return param -> NormalizationEnum.GLOBAL_AVG;
    }
}
