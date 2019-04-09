package com.sample.springboot.util.exception;

import com.sample.springboot.util.exception.domain.Error;

/**
 * @author: Lucifer
 * @date: 2018/8/15 15:21
 * @description: 自定义错误异常
 */
public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = -4598947623799548505L;
    /**
     * 错误信息类
     */
    protected Error error;


    public ApplicationException() {
    }

    public ApplicationException(Error error) {
        this.error = error;
    }

    public ApplicationException(Error error, Throwable cause) {
        super(cause);
        this.error = error;
    }


    public ApplicationException(String message) {
        super(message);
        error = new Error(Error.DefaultErrorCode, message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
        error = new Error(Error.DefaultErrorCode, message);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        error = new Error(Error.DefaultErrorCode, message);
    }


    @Override
    public String getMessage() {
        if (org.springframework.util.ObjectUtils.nullSafeEquals(null, error)) {
            return super.getMessage();
        } else {
            return error.toString();
        }
    }

    public Error getError() {
        return error;
    }
}
