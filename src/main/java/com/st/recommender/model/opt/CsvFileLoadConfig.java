package com.st.recommender.model.opt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsvFileLoadConfig {
    private Class clazz;
    private String delimiter;
    private List<String> header;
    private Function<Map<String, String>, Object> dataConverter;
    private Predicate<Object> dataJudge;
}
