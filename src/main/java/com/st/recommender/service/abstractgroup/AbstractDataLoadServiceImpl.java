package com.st.recommender.service.abstractgroup;

import com.alibaba.fastjson.JSON;
import com.st.recommender.constants.DataLoadTypeEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.model.opt.CsvFileLoadConfig;
import com.st.recommender.service.DataLoadService;
import com.st.recommender.service.FileOperationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
@Component
public abstract class AbstractDataLoadServiceImpl<T> extends AbstractDataProcessorImpl implements DataLoadService {

    @Autowired
    protected FileOperationService fileOperationService;

    public Map<Class, List> loadData(Param param) {

        Map<Class, List> result = new HashMap<>();
        Map<String, Class> pojoClasses = pojoClass();
        Map<String, String> filePathConfig = filePathConverter(param);
        log.info("Built filePathConfig info:[{}]", JSON.toJSONString(filePathConfig));
        Map<String, DataLoadTypeEnum> dataTypeConfig = dataLoadTypeFinder(param);
        Map<String, CsvFileLoadConfig> csvFileConfig = csvFileLoadConfigBuilder(param);

        pojoClasses.entrySet().forEach(e -> {
            String key = e.getKey();
            Class value = e.getValue();

            String filePath = filePathConfig.get(key);
            DataLoadTypeEnum dataLoadType = dataTypeConfig.get(key);
            CsvFileLoadConfig csvConfig = csvFileConfig.get(key);
            if (StringUtils.isBlank(filePath) || (DataLoadTypeEnum.CSV.equals(dataLoadType) && Objects.isNull(csvConfig))) {
                return;
            }

            List oriData;
            switch (dataLoadType) {
                case CSV:
                    oriData = fileOperationService.loadFileAsCsv(filePath, csvConfig);
                    break;

                case JSON_JSON:
                    oriData = fileOperationService.loadFileAsCamelJson(filePath);
                    break;

                case JSON_POJO:
                default:
                    oriData = fileOperationService.loadFileAsPojo(filePath, value);
                    break;
            }

            log.info("Loaded [{}] data lines for:[{}] from:[{}]", oriData.size(), key, filePath);
            result.put(value, oriData);
        });

        return result;
    }

    protected Map<String, String> filePathConverter(Param param) { return param.getInputFilePath(); }

    protected abstract Map<String, Class> pojoClass();

    protected Map<String, CsvFileLoadConfig> csvFileLoadConfigBuilder(Param param) { return new HashMap<>(); }

    protected abstract Map<String, DataLoadTypeEnum> dataLoadTypeFinder(Param param);

    protected String delimiterFinder(Param param) { return ","; }

    protected List<String> headerFinder(Param param) { return new ArrayList<>(); }

    protected Function<Map<String, String>, Object> dataConverter() {
        return map -> new Object();
    }

    protected Predicate<Object> dataJudge() {
        return Objects::nonNull;
    }
}
