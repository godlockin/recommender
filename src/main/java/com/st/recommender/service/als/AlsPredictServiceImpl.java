package com.st.recommender.service.als;

import com.alibaba.fastjson.JSON;
import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.BaseEnum;
import com.st.recommender.constants.Constants.AlgorithmMapping;
import com.st.recommender.model.input.Param;
import com.st.recommender.model.opt.als.ScoreModel;
import com.st.recommender.service.*;
import com.st.recommender.service.abstractgroup.AbstractPredictServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public abstract class AlsPredictServiceImpl extends AbstractPredictServiceImpl implements PredictService, AlsPredictService, DataProcessor {

    private Map<BaseEnum, DataLoadService> dataLoaderMap;
    private Map<BaseEnum, DataConvertService> dataConverterMap;
    private Map<BaseEnum, DataNormalizeService> dataNormalizerMap;
    private Map<BaseEnum, AlsPredictService> dataPredictorMap;

    @Override
    public String predict(Param param) {
        AlgorithmEnum algorithm = param.getAlgorithm();

        List oriData = Optional.ofNullable(dataLoaderMap.get(algorithm))
                .flatMap(loader -> Optional.ofNullable(loader.loadData(param))).orElse(new ArrayList<>());
        if (CollectionUtils.isEmpty(oriData)) {
            return "";
        }

        List cleanData = Optional.ofNullable(dataConverterMap.get(algorithm))
                .flatMap(converter -> Optional.ofNullable(converter.convert(oriData))).orElse(new ArrayList<>());
        if (CollectionUtils.isEmpty(cleanData)) {
            return "";
        }

        List normalData = Optional.ofNullable(dataNormalizerMap.get(algorithm))
                .flatMap(normalizer -> Optional.ofNullable(normalizer.normalize(cleanData))).orElse(new ArrayList());
        if (CollectionUtils.isEmpty(cleanData)) {
            return "";
        }

        Map<String, List<String>> predictResult = Optional.ofNullable(dataPredictorMap.get(algorithm))
                .flatMap(predictor -> Optional.ofNullable(predictor.doPredict(param, normalData))).orElse(new HashMap<>());
        if (CollectionUtils.isEmpty(predictResult)) {
            return "";
        }

        log.info(JSON.toJSONString(predictResult));
        predictResult.forEach((k, v) -> {
            if (!CollectionUtils.isEmpty(v)) {
                log.info("[{}]:=[{}]", k, v);
            }
        });
        return "";
    }

    public abstract Map<String, List<String>> doPredict(Param Param, List<List<ScoreModel>> normalData);

    @PostConstruct
    void init() {
        dataLoaderMap = (Map<BaseEnum, DataLoadService>) buildMap(DataLoadService.class);
        dataConverterMap = (Map<BaseEnum, DataConvertService>) buildMap(DataConvertService.class);
        dataNormalizerMap = (Map<BaseEnum, DataNormalizeService>) buildMap(DataNormalizeService.class);
        dataPredictorMap = (Map<BaseEnum, AlsPredictService>) buildMap(AlsPredictService.class);
        log.info("[{}] init", processorName());
    }

    private Map<BaseEnum, ? extends DataProcessor> buildMap(Class<? extends DataProcessor> clazz) {
        return context.getBeansOfType(clazz).values().stream()
                .filter(processor -> AlgorithmEnum.ALS.equals(AlgorithmMapping.MAPPING.get(processor.algorithm())))
                .collect(Collectors.toMap(DataProcessor::algorithm, k -> k));
    }

    @Override
    public BaseEnum algorithm() { return AlgorithmEnum.ALS; }

}
