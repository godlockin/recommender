package com.st.recommender.model.opt.labelbase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemSingleLabelsMatrix {
    private long idx;
    private double[] matrix;

    public ItemSingleLabelsMatrix(long idx, int matrixSize) {
        this.idx = idx;
        this.matrix = new double[matrixSize];
    }
}
