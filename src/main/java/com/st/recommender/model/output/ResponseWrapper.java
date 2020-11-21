package com.st.recommender.model.output;

import com.st.recommender.constants.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T> {

    private int code;

    private String message;

    private T data;

    public static <T> ResponseWrapper<T> success(T data) {
        ResultEnum resultEnum = ResultEnum.SUCCESS;
        return new ResponseWrapper<>(resultEnum.getCode(), resultEnum.getMessage(), data);
    }

    public static <T> ResponseWrapper<T> failure(ResultEnum resultEnum) {
        return new ResponseWrapper<>(resultEnum.getCode(), resultEnum.getMessage(), null);
    }

    public static <T> ResponseWrapper<T> failure(int code, String message) {
        return new ResponseWrapper<>(code, message, null);
    }
}
