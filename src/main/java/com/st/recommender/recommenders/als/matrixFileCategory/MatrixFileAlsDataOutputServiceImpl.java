package com.st.recommender.recommenders.als.matrixFileCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.BaseEnum;
import com.st.recommender.recommenders.als.anchorToLeafCategory.AtLCAlsDataOutputServiceImpl;
import com.st.recommender.service.DataOutputService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MatrixFileAlsDataOutputServiceImpl extends AtLCAlsDataOutputServiceImpl implements DataOutputService {

    @Value("${MFAC_ALS_OUT_ITEM_DETAILS_DELIMITER:,}")
    private String ITEM_DETAILS_DELIMITER;
    @Value("${MFAC_ALS_OUT_ITEM_COLUMN_DELIMITER:\t}")
    private String ITEM_COLUMN_DELIMITER;
    @Value("${MFAC_ALS_OUT_ITEM_LINE_DELIMITER:\n}")
    private String ITEM_LINE_DELIMITER;
    @Value("${MFAC_ALS_OUT_DEFAULT_FILE_PATTERN:%s_%s_result.csv}")
    private String DEFAULT_FILE_PATTERN;
    @Value("${MFAC_ALS_OUT_HEADERS:userId,itemsList}")
    private List<String> FILE_HEADERS;

    @Override
    public BaseEnum algorithm() {
        return AlgorithmEnum.MATRIX_FILE_ALS;
    }

    protected String getItemLineDelimiter() {
        return ITEM_LINE_DELIMITER;
    }

    protected String getItemColumnDelimiter() {
        return ITEM_COLUMN_DELIMITER;
    }

    protected String getFilePattern() {
        return DEFAULT_FILE_PATTERN;
    }

    protected List<String> getFileHeaders() {
        return FILE_HEADERS;
    }

    protected String getItemDetailsDelimiter() {
        return ITEM_DETAILS_DELIMITER;
    }
}
