package com.st.recommender.recommenders.als.matrixFileCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.DataLoadTypeEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.model.opt.CsvFileLoadConfig;
import com.st.recommender.model.origin.AnchorLeafRaw;
import com.st.recommender.service.DataLoadService;
import com.st.recommender.service.abstractgroup.AbstractDataLoadServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
@Service
public class MatrixFileAlsDataLoadServiceImpl extends AbstractDataLoadServiceImpl implements DataLoadService {

    @Value("${MFAC_ALS_INPUT_PATH_KEY:matrix_file}")
    private String INPUT_PATH_KEY;

    @Override
    public AlgorithmEnum algorithm() {
        return AlgorithmEnum.MATRIX_FILE_ALS;
    }

    protected String getInputPathKey() {
        return INPUT_PATH_KEY;
    }

    @Override
    protected Map<String, Class> pojoClass() {
        Map<String, Class> map = new HashMap<>();
        map.put(getInputPathKey(), AnchorLeafRaw.class);
        return map;
    }

    protected Map<String, DataLoadTypeEnum> dataLoadTypeFinder(Param param) {
        Map<String, DataLoadTypeEnum> map = new HashMap<>();
        map.put(getInputPathKey(), DataLoadTypeEnum.CSV);
        return map;
    }

    @Override
    protected Map<String, CsvFileLoadConfig> csvFileLoadConfigBuilder(Param param) {
        Map<String, CsvFileLoadConfig> configMap = new HashMap<>();
        CsvFileLoadConfig config = CsvFileLoadConfig.builder()
                .clazz(AnchorLeafRaw.class)
                .delimiter(delimiterFinder(param))
                .dataConverter(dataConverter())
                .dataJudge(dataJudge())
                .header(headerFinder(param))
                .build();

        configMap.put(getInputPathKey(), config);
        return configMap;
    }

    protected List<String> headerFinder(Param param) {
        return Optional.ofNullable(param.getSeedList())
                .orElse(Arrays.asList(
                        "anchor_id"
                        , "item_id"
                        , "count"
                ));
    }

    @Override
    protected Predicate<Object> dataJudge() {
        return o -> {
            AnchorLeafRaw anchorLeafRaw = (AnchorLeafRaw) o;
            return StringUtils.isNoneBlank(anchorLeafRaw.getAnchorID(), anchorLeafRaw.getLeafCategory()) &&
                    0L < Optional.ofNullable(anchorLeafRaw.getCount()).orElse(0L);
        };
    }

    @Override
    protected Function<Map<String, String>, Object> dataConverter() {
        return map -> AnchorLeafRaw.builder()
                .anchorID(map.getOrDefault("anchor_id", ""))
                .leafCategory(map.getOrDefault("item_id", ""))
                .count(Optional.ofNullable(map.get("count"))
                        .filter(str -> StringUtils.isNoneBlank(str) && !"\"\"".equalsIgnoreCase(str))
                        .flatMap(str -> Optional.of(Long.parseLong(str))).orElse(1L))
                .build();
    }
}