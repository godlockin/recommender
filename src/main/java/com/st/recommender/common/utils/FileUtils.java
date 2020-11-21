package com.st.recommender.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class FileUtils {

    public static List<String> loadFile(String path) {
        File file = new File(path);

        if (!file.exists() || file.isDirectory()) {
            log.error("Illegal file for path:[{}]", path);
        }

        List<String> fileLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines()
                    .filter(Objects::nonNull)
                    .filter(StringUtils::isNotBlank)
                    .map(String::trim)
                    .forEach(fileLines::add);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Got error during we load file:[{}], msg:[{}]", path, e);
        }
        return fileLines;
    }
}
