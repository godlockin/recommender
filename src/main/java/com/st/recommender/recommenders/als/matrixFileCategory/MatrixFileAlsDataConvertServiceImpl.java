package com.st.recommender.recommenders.als.matrixFileCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.model.origin.AnchorLeafRaw;
import com.st.recommender.service.DataConvertService;
import com.st.recommender.service.abstractgroup.AbstractDataConvertServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class MatrixFileAlsDataConvertServiceImpl extends AbstractDataConvertServiceImpl<Map<Class, List>, List> implements DataConvertService<Map<Class, List>, List> {

    @Override
    public AlgorithmEnum algorithm() {
        return AlgorithmEnum.MATRIX_FILE_ALS;
    }

    @Override
    protected Function<Map<Class, List>, List> dataConverter() {
        return map -> Collections.singletonList(map.getOrDefault(AnchorLeafRaw.class, new ArrayList<>()));
    }
}
