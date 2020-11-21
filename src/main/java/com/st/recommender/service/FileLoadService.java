package com.st.recommender.service;

import com.alibaba.fastjson.JSONArray;

import java.util.List;

public interface FileLoadService {

    List<String> loadFile(String path);

    JSONArray loadFileAsJson(String path);

    JSONArray loadFileAsCamelJson(String path);

    <T> List<T> loadFileAsPojo(String path, Class<T> clazz);
}
