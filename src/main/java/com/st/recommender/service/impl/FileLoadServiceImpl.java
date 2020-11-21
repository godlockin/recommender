package com.st.recommender.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.st.recommender.common.utils.FileUtils;
import com.st.recommender.service.FileLoadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FileLoadServiceImpl implements FileLoadService {

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

        JSONArray camelJson = handleJSONArray(oriJson);
        return CollectionUtils.isEmpty(camelJson) ? oriJson : camelJson;
    }

    @Override
    public <T> List<T> loadFileAsPojo(String path, Class<T> clazz) {
        JSONArray jsonArray = loadFileAsCamelJson(path);
        return jsonArray.stream().map(json -> JSON.parseObject(JSON.toJSONString(json), clazz)).collect(Collectors.toList());
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
