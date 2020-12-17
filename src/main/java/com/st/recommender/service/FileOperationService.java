package com.st.recommender.service;

import com.alibaba.fastjson.JSONArray;
import com.st.recommender.model.opt.CsvFileLoadConfig;

import java.util.List;
import java.util.Map;

public interface FileOperationService {

    List<String> loadFile(String path);

    JSONArray loadFileAsJson(String path);

    JSONArray loadFileAsCamelJson(String path);

    Map<Class, List> loadFileAsJson(Map<String, String> pathMap, Map<String, Class> clazzMap);

    <T> List<T> loadFileAsPojo(String path, Class<T> clazz);

    Map<Class, List> loadFileAsPojo(Map<String, String> pathMap, Map<String, Class> clazzMap);

    Map<Class, List> loadFileAsCsv(Map<String, String> pathMap, Map<String, CsvFileLoadConfig> configMap);

    List loadFileAsCsv(String path, CsvFileLoadConfig csvFileLoadConfig);

    String writeFile(String path, List<String> data);
}
