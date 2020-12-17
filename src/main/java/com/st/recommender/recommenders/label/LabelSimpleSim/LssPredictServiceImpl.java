package com.st.recommender.recommenders.label.LabelSimpleSim;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.BaseEnum;
import com.st.recommender.recommenders.label.LabelBasePredictService;
import com.st.recommender.recommenders.label.LabelBasePredictServiceImpl;
import com.st.recommender.service.DataProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LssPredictServiceImpl extends LabelBasePredictServiceImpl implements LabelBasePredictService, DataProcessor {

    @Override
    public BaseEnum algorithm() {
        return AlgorithmEnum.LABEL_SIMPLE_SIM;
    }

}
