package com.st.recommender.service.als.leafToAnchorCategory.singleAnchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.BaseEnum;
import com.st.recommender.service.DataOutputService;
import com.st.recommender.service.als.anchorToLeafCategory.AtLCAlsDataOutputServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LtACAlsDataOutputServiceImpl extends AtLCAlsDataOutputServiceImpl implements DataOutputService {

    @Value("${SATLC_ALS_OUT_ITEM_DETAILS_DELIMITER:,}")
    private String ITEM_DETAILS_DELIMITER;
    @Value("${SATLC_ALS_OUT_ITEM_COLUMN_DELIMITER:\t}")
    private String ITEM_COLUMN_DELIMITER;
    @Value("${SATLC_ALS_OUT_ITEM_LINE_DELIMITER:\n}")
    private String ITEM_LINE_DELIMITER;
    @Value("${SATLC_ALS_OUT_DEFAULT_FILE_PATTERN:%s_%s_result.csv}")
    private String DEFAULT_FILE_PATTERN;
    @Value("${SATLC_ALS_OUT_HEADERS:userId,itemsList}")
    private List<String> FILE_HEADERS;

    @Override
    public BaseEnum algorithm() { return AlgorithmEnum.LEAF_TO_ANCHOR_CATEGORY; }

    protected String getItemLineDelimiter() { return ITEM_LINE_DELIMITER; }

    protected String getItemColumnDelimiter() { return ITEM_COLUMN_DELIMITER; }

    protected String getFilePattern() { return DEFAULT_FILE_PATTERN; }

    protected List<String> getFileHeaders() { return FILE_HEADERS; }

    protected String getItemDetailsDelimiter() { return ITEM_DETAILS_DELIMITER; }
}
