package com.simple.sample.exception;

/**
 * @author: kiturone
 * @date: 2019/8/15 10:43
 * @description:
 */
public class BaseException extends Exception {

    private Integer code;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
