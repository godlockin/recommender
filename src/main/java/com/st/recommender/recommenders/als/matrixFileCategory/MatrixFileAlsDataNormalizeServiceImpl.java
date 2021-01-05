package com.st.recommender.recommenders.als.matrixFileCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.NormalizationEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.model.opt.als.ScoreModel;
import com.st.recommender.model.origin.AnchorLeafRaw;
import com.st.recommender.recommenders.als.anchorToLeafCategory.AtLCAlsDataNormalizeServiceImpl;
import com.st.recommender.service.DataNormalizeService;
import com.st.recommender.service.abstractgroup.AbstractDataNormalizeServiceImpl;
import com.st.recommender.service.common.NormalizeFuncUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MatrixFileAlsDataNormalizeServiceImpl extends AtLCAlsDataNormalizeServiceImpl implements DataNormalizeService<List<List<AnchorLeafRaw>>, List<List<ScoreModel>>> {

    @Override
    public AlgorithmEnum algorithm() {
        return AlgorithmEnum.MATRIX_FILE_ALS;
    }

}
