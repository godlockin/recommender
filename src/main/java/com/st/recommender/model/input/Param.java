package com.st.recommender.model.input;

import com.st.recommender.constants.AlgorithmEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Param {
    protected AlgorithmEnum algorithm;
    private Map<String, String> inputFilePath;
    private String outputFilePath;

    private Boolean selfDistinct = true;
    private Integer topN = 100;
    private Integer topSimilarity = 20;
}
