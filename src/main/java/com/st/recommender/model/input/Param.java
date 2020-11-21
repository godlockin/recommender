package com.st.recommender.model.input;

import com.st.recommender.constants.AlgorithmEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Param {
    protected AlgorithmEnum algorithm;
    private String inputFilePath;
    private String outputFilePath;
    private Integer topN = 100;
    private Integer topSimilarity = 20;
}
