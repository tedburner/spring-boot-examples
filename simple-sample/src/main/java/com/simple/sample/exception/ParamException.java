package com.simple.sample.exception;

/**
 * @author: lingjun.jlj
 * @date: 2019/8/15 10:44
 * @description:
 */
public class ParamException extends BaseException {

    private Integer code;

    public ParamException(String message) {
        super(message);
    }

    public ParamException(Integer code, String message) {
        super(code, message);
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
