package com.st.recommender.model.input;

import com.st.recommender.constants.AlgorithmEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Param {
    protected AlgorithmEnum algorithm;
    protected Double normRange = 100D;
    protected Map<String, String> inputFilePath;
    protected String outputFilePath;

    private Integer parallel = 20;
    private Integer topN = 100;
    private String similarityFunc = "EUCLIDEAN_DISTANCE";
    private String normalizationFunc = "MAX_MIN_RANGE";
    private Integer topSimilarity = 20;

    // als
    private Boolean selfDistinct = true;

    // label base
    private List<String> seedList;
}
