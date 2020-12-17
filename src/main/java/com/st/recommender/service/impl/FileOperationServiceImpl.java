package com.st.recommender.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.st.recommender.common.utils.FileUtils;
import com.st.recommender.model.opt.CsvFileLoadConfig;
import com.st.recommender.service.FileOperationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FileOperationServiceImpl implements FileOperationService {

    @Value("${DEFAULT_OUT_ITEM_LINE_DELIMITER:\n}")
    private String ITEM_LINE_DELIMITER = "\n";

    @Override
    public List<String> loadFile(String path) {
        List<String> fileContents = new ArrayList<>();
        if (StringUtils.isBlank(path)) {
            log.error("Empty file path");
            return fileContents;
        }

        fileContents = FileUtils.loadFile(path);
        log.info("Got {} content in total for path:[{}]", fileContents.size(), path);
        return fileContents;
    }

    @Override
    public JSONArray loadFileAsJson(String path) {
        JSONArray array = new JSONArray();
        List<String> contents = loadFile(path);
        contents.stream()
                .map(JSONObject::parseObject)
                .filter(obj -> !CollectionUtils.isEmpty(obj))
                .forEach(array::add);
        return array;
    }

    @Override
    public JSONArray loadFileAsCamelJson(String path) {
        JSONArray oriJson = loadFileAsJson(path);
        if (CollectionUtils.isEmpty(oriJson)) {
            return oriJson;
        }

        log.info("Loaded {} json data lines from:[{}]", oriJson.size(), path);
        JSONArray camelJson = handleJSONArray(oriJson);
        return CollectionUtils.isEmpty(camelJson) ? oriJson : camelJson;
    }

    @Override
    public Map<Class, List> loadFileAsJson(Map<String, String> pathMap, Map<String, Class> clazzMap) {
        return pathMap.entrySet().parallelStream()
                .filter(e -> clazzMap.containsKey(e.getKey()))
                .map(e -> MutablePair.of(clazzMap.get(e.getKey()), e.getValue()))
                .map(pair -> MutablePair.of(pair.getKey(), loadFileAsCamelJson(pair.getValue())))
                .filter(pair -> !CollectionUtils.isEmpty(pair.getValue()))
                .collect(Collectors.toMap(MutablePair::getKey, MutablePair::getValue));
    }

    @Override
    public <T> List loadFileAsPojo(String path, Class<T> clazz) {
        JSONArray jsonArray = loadFileAsCamelJson(path);
        return jsonArray.stream()
                .map(json -> JSON.parseObject(JSON.toJSONString(json), clazz))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Class, List> loadFileAsPojo(Map<String, String> pathMap, Map<String, Class> clazzMap) {
        return pathMap.entrySet().parallelStream()
                .filter(e -> clazzMap.containsKey(e.getKey()))
                .map(e -> MutablePair.of(e.getValue(), clazzMap.get(e.getKey())))
                .map(pair -> MutablePair.of(pair.getValue(), loadFileAsPojo(pair.getKey(), pair.getValue())))
                .filter(pair -> !CollectionUtils.isEmpty(pair.getValue()))
                .collect(Collectors.toMap(MutablePair::getKey, MutablePair::getValue));
    }

    @Override
    public Map<Class, List> loadFileAsCsv(Map<String, String> pathMap, Map<String, CsvFileLoadConfig> configMap) {
        return pathMap.entrySet().parallelStream()
                .filter(e -> Objects.nonNull(configMap.get(e.getKey())))
                .map(e -> MutablePair.of(e.getValue(), configMap.get(e.getKey())))
                .map(pair -> MutablePair.of(pair.getValue().getClazz(), loadFileAsCsv(pair.getKey(), pair.getValue())))
                .filter(pair -> !CollectionUtils.isEmpty(pair.getValue()))
                .collect(Collectors.toMap(MutablePair::getKey, MutablePair::getValue));
    }

    @Override
    public List<Object> loadFileAsCsv(String path, CsvFileLoadConfig csvFileLoadConfig) {

        String delimiter = csvFileLoadConfig.getDelimiter();
        List<String> header = csvFileLoadConfig.getHeader();
        Function<Map<String, String>, Object> dataConverter = csvFileLoadConfig.getDataConverter();
        Predicate<Object> dataJudge = csvFileLoadConfig.getDataJudge();

        List<Object> result = new ArrayList<>();
        List<String> contents = loadFile(path).stream()
                .filter(str -> str.contains(delimiter))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(contents)) {
            return result;
        }

        log.info("Loaded {} csv data lines from:[{}]", contents.size(), path);
        String firstLine = contents.get(0);
        if (CollectionUtils.isEmpty(header)) {
            if (contents.size() <= 1) {
                return result;
            }
            header.addAll(Arrays.asList(firstLine.split(delimiter)));
        }

        int columnNum = header.size();
        contents = contents.subList(1, contents.size());
        return contents.stream().map(str -> str.split(delimiter))
                .filter(array -> 0 < array.length)
                .map(Arrays::asList)
                .map(list -> {
                    Map<String, String> dataLine = new HashMap<>();
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        if (i > columnNum - 1) {
                            break;
                        }

                        dataLine.put(header.get(i), list.get(i));
                    }
                    return dataLine;
                })
                .map(dataConverter)
                .filter(dataJudge)
                .collect(Collectors.toList());
    }

    @Override
    public String writeFile(String path, List<String> data) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(new File(path)))) {
            br.write(String.join(ITEM_LINE_DELIMITER, data));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return path;
    }

    private JSONObject handleJSONObject(JSONObject root) {
        JSONObject result = new JSONObject();
        root.entrySet().stream()
                .filter(e -> StringUtils.isNotBlank(e.getKey()) && Objects.nonNull(e.getValue()))
                .forEach(e -> {
                    String key = e.getKey();
                    Object value = e.getValue();
                    String[] keyItems = key.split("_");
                    String cleanKey = key;
                    Object cleanValue = value;
                    if (keyItems.length > 1) {
                        if ("_id".equalsIgnoreCase(key)) {
                            JSONObject idObj = (JSONObject) value;
                            cleanValue = idObj.getString("oid");
                        } else {
                            cleanKey = keyItems[0] + Arrays.stream(keyItems)
                                    .filter(StringUtils::isNotBlank)
                                    .skip(1).map(str -> {
                                        if (1 == str.length()) {
                                            return str;
                                        }

                                        String first = str.substring(0, 1);
                                        String follow = str.substring(1);
                                        return first.toUpperCase() + follow;
                                    }).collect(Collectors.joining());
                        }
                    }

                    if (value instanceof JSONObject) {
                        cleanValue = handleJSONObject((JSONObject) value);
                    } else if (value instanceof JSONArray) {
                        cleanValue = handleJSONArray((JSONArray) value);
                    }

                    result.put(cleanKey, cleanValue);
                });
        return result;
    }

    private JSONArray handleJSONArray(JSONArray root) {
        JSONArray result = new JSONArray();
        root.stream().map(obj -> {
            if (obj instanceof JSONObject) {
                return handleJSONObject((JSONObject) obj);
            } else if (obj instanceof JSONArray) {
                return handleJSONArray((JSONArray) obj);
            }
            return obj;
        }).forEach(result::add);
        return result;
    }

}
