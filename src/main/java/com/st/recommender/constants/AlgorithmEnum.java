package com.st.recommender.constants;

public enum AlgorithmEnum implements BaseEnum {

    BASE(0, "BASE"), ALS(1, "ALS"), ANCHOR_TO_LEAF_CATEGORY(11, "AtLC"), SINGLE_ANCHOR_TO_LEAF_CATEGORY(12, "SAtLC"), LEAF_TO_ANCHOR_CATEGORY(12, "LtAC"), LABEL_BASE(2, "LB"), LABEL_SIMPLE_SIM(21, "LSS");

    private Integer code;
    private String message;

    AlgorithmEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
