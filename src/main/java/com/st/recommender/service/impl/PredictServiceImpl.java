package com.st.recommender.service.impl;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.BaseEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.service.DataProcessor;
import com.st.recommender.service.PredictService;
import com.st.recommender.service.abstractgroup.AbstractPredictServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PredictServiceImpl extends AbstractPredictServiceImpl implements PredictService, DataProcessor {

    @Override
    public String predict(Param param) {
        return Optional.ofNullable(dataPredictorMap.get(param.getAlgorithm()))
                .flatMap(predictService -> Optional.ofNullable(predictService.predict(param))).orElse("");
    }

    @Override
    public BaseEnum algorithm() {
        return AlgorithmEnum.BASE;
    }
}
