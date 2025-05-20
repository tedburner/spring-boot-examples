package com.simple.sample.util.http;

/**
 * @author kiturone
 * @create 2017-11-23
 **/
public enum ResponseCodeEnum {
    Success(0, "请求成功"),
    Fail(1000, "请求失败"),
    PARAM_ERROR(1001, "参数错误"),
    ;


    private int code;
    private String message;

    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
