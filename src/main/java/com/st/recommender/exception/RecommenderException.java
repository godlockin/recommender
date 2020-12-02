package com.st.recommender.exception;

import com.st.recommender.constants.ResultEnum;
import lombok.Data;

@Data
public class RecommenderException extends RuntimeException {

    private Integer code;

    public RecommenderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public RecommenderException(ResultEnum resultEnum, String message) {
        super(message);
        this.code = resultEnum.getCode();
    }

    public RecommenderException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}