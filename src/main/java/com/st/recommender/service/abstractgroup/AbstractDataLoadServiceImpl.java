package com.st.recommender.service.abstractgroup;

import com.alibaba.fastjson.JSON;
import com.st.recommender.constants.DataLoadTypeEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.model.opt.CsvFileLoadConfig;
import com.st.recommender.service.DataLoadService;
import com.st.recommender.service.FileOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Component
public abstract class AbstractDataLoadServiceImpl<T> extends AbstractDataProcessorImpl implements DataLoadService {

    @Autowired
    protected FileOperationService fileOperationService;

    public Map<Class, List> loadData(Param param) {

        Map<Class, List> oriData;
        Map<String, String> filePath = filePathConverter().apply(param);
        log.info("Built filePath info:[{}]", JSON.toJSONString(filePath));
        switch (dataLoadTypeFinder().apply(param)) {
            case CSV:
                Map<String, CsvFileLoadConfig> csvFileConfig = csvFileLoadConfigBuilder().apply(param);
                log.info("Built csvConfig:[{}]", JSON.toJSONString(csvFileConfig));
                oriData = fileOperationService.loadFileAsCsv(filePath, csvFileConfig);
                break;
            case JSON:
            default:
                String pojoStr = pojoClass().entrySet().stream().map(e -> String.format("[%s->%s]", e.getKey(), e.getValue().getSimpleName())).collect(Collectors.joining(","));
                log.info("Built pojo info:[{}]", pojoStr);
                oriData = fileOperationService.loadFileAsPojo(filePath, pojoClass());
                break;
        }

        String oriDataStr = oriData.entrySet().stream().map(e -> String.format("[%s->%s]", e.getKey(), e.getValue().size())).collect(Collectors.joining(","));
        log.info("Load as original data: {}", oriDataStr);
        return oriData;
    }

    protected abstract Function<Param, Map<String, String>> filePathConverter();

    protected abstract Map<String, Class> pojoClass();

    protected abstract Function<Param, Map<String, CsvFileLoadConfig>> csvFileLoadConfigBuilder();

    protected Function<Param, DataLoadTypeEnum> dataLoadTypeFinder() { return p -> DataLoadTypeEnum.JSON; };

    protected Function<Param, String> delimiterFinder() { return p -> ","; }

    protected Function<Param, List<String>> headerFinder() { return p -> new ArrayList<>(); }

    protected abstract Function<Map<String, String>, Object> dataConverter();

    protected abstract Predicate<Object> dataJudge();
}
