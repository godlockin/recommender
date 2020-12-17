package com.st.recommender.constants;

public enum ResultEnum implements BaseEnum {

    SUCCESS(1, "成功"), FAILURE(0, "失败"), PARAMETER_CHECK(21, "参数校验失败"), REMOTE_QUERY(31, "远程请求失败");

    private Integer code;
    private String message;

    ResultEnum(int code, String message) {
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
