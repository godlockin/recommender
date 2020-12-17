package com.st.recommender.recommenders.als.anchorToLeafCategory;

import com.st.recommender.common.utils.DateUtils;
import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.BaseEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.model.opt.als.AlsParam;
import com.st.recommender.service.DataOutputService;
import com.st.recommender.service.abstractgroup.AbstractDataOutputServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class AtLCAlsDataOutputServiceImpl extends AbstractDataOutputServiceImpl implements DataOutputService {

    @Value("${ATLC_ALS_OUT_ITEM_DETAILS_DELIMITER:,}")
    private String ITEM_DETAILS_DELIMITER;
    @Value("${ATLC_ALS_OUT_ITEM_COLUMN_DELIMITER:\t}")
    private String ITEM_COLUMN_DELIMITER;
    @Value("${ATLC_ALS_OUT_ITEM_LINE_DELIMITER:\n}")
    private String ITEM_LINE_DELIMITER;
    @Value("${ATLC_ALS_OUT_DEFAULT_FILE_PATTERN:%s_%s_result.csv}")
    private String DEFAULT_FILE_PATTERN;
    @Value("${ATLC_ALS_OUT_HEADERS:userId,itemsList}")
    private List<String> FILE_HEADERS;

    @Override
    public BaseEnum algorithm() {
        return AlgorithmEnum.ANCHOR_TO_LEAF_CATEGORY;
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

    @Override
    protected String doWriteFile(String filePath, Param funcParam, Object workData) {
        List<String> dataLines = (List<String>) workData;
        AlsParam alsParam = (AlsParam) funcParam;
        List<String> headers = alsParam.getHeaders();
        List<String> outLines = new ArrayList<>();
        outLines.add(String.join(getItemColumnDelimiter(), headers));
        outLines.addAll(dataLines);
        try (BufferedWriter br = new BufferedWriter(new FileWriter(new File(filePath)))) {
            br.write(String.join(getItemLineDelimiter(), outLines));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return filePath;
    }

    @Override
    protected Function<Param, String> filePathConverter() {
        return param -> {
            String outputFilePath = param.getOutputFilePath();
            if (StringUtils.isNotBlank(outputFilePath)) {
                return outputFilePath;
            }

            return String.format(getFilePattern(), DateUtils.formatZonedDateTime(), algorithm());
        };
    }

    @Override
    protected Function<Param, ? extends Param> configConverter() {
        return param -> {
            AlsParam alsParam = new AlsParam(param);
            alsParam.setHeaders(getFileHeaders());
            return alsParam;
        };
    }

    @Override
    protected <T, R> Function<T, R> dataConverter() {
        return data -> {
            Map<String, List<String>> workData = (Map<String, List<String>>) data;
            List<String> dataLines = new ArrayList<>();
            workData.entrySet().parallelStream()
                    .filter(e -> StringUtils.isNotBlank(e.getKey()) && e.getValue().stream().noneMatch(StringUtils::isBlank))
                    .map(e -> String.format("%s%s%s", e.getKey(), getItemColumnDelimiter(), String.join(getItemDetailsDelimiter(), e.getValue())))
                    .forEachOrdered(dataLines::add);
            return (R) dataLines;
        };
    }

}
