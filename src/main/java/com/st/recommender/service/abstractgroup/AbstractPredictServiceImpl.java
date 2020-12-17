package com.st.recommender.service.abstractgroup;

import com.st.recommender.constants.BaseEnum;
import com.st.recommender.constants.DistanceEnum;
import com.st.recommender.constants.NormalizationEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.service.*;
import com.st.recommender.service.common.DistanceFuncUtils;
import com.st.recommender.service.common.NormalizeFuncUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public abstract class AbstractPredictServiceImpl extends AbstractDataProcessorImpl implements PredictService {

    protected Map<BaseEnum, DataLoadService> dataLoaderMap;
    protected Map<BaseEnum, DataConvertService> dataConverterMap;
    protected Map<BaseEnum, DataNormalizeService> dataNormalizerMap;
    protected Map<BaseEnum, PredictService> dataPredictorMap;
    protected Map<BaseEnum, DataOutputService> dataOutputServiceMap;
    protected Map<BaseEnum, PredictService> predictors;

    public abstract String predict(Param param);

    @PostConstruct
    void init() {
        dataLoaderMap = (Map<BaseEnum, DataLoadService>) buildMap(DataLoadService.class);
        dataConverterMap = (Map<BaseEnum, DataConvertService>) buildMap(DataConvertService.class);
        dataNormalizerMap = (Map<BaseEnum, DataNormalizeService>) buildMap(DataNormalizeService.class);
        dataPredictorMap = (Map<BaseEnum, PredictService>) buildMap(PredictService.class);
        dataOutputServiceMap = (Map<BaseEnum, DataOutputService>) buildMap(DataOutputService.class);
        log.info("[{}] init", processorName());
    }

    private Map<BaseEnum, ? extends DataProcessor> buildMap(Class<? extends DataProcessor> clazz) {
        return context.getBeansOfType(clazz).values().stream().collect(Collectors.toMap(DataProcessor::algorithm, k -> k));
    }

    protected Function<Param, Integer> workParallel() {
        return param -> Optional.ofNullable(param.getParallel()).orElse(20);
    }

    protected Function<Param, BiFunction<Double, double[], double[]>> normalizationCalc() {
        return param -> NormalizeFuncUtils.findNormalizeFunc(NormalizationEnum.valueOf(param.getNormalizationFunc()));
    }

    protected Function<Param, Integer> topNSimilar() {
        return param -> Optional.ofNullable(param.getTopSimilarity()).orElse(20);
    }

    protected Function<Param, Integer> topNItem() {
        return param -> Optional.ofNullable(param.getTopN()).orElse(100);
    }

    protected Function<Param, Boolean> selfDistinct() {
        return param -> Optional.ofNullable(param.getSelfDistinct()).orElse(true);
    }

    protected Function<Param, BiFunction<double[], double[], Double>> similarityCalc() {
        return param -> DistanceFuncUtils.findSimilarityFunc(DistanceEnum.valueOf(param.getSimilarityFunc()));
    }

    protected Function<Param, Double> normalizationRange() {
        return param -> Optional.ofNullable(param.getNormRange()).orElse(100D);
    }
}
