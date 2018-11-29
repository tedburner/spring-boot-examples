package com.example.springboot.utils.exception;

import com.example.springboot.utils.exception.domain.Error;

import static com.example.springboot.utils.http.ResponseCodeEnum.PARAM_ERROR;

/**
 * @author: Lucifer
 * @date: 2018/8/15 15:21
 * @description: 参数错误异常
 */
public class ParamException extends ApplicationException {

    private static final long serialVersionUID = -1500549226584891801L;

    public ParamException() {
        super(Error.builder()
                .code(PARAM_ERROR.getCode())
                .message(PARAM_ERROR.getMessage())
                .build());
    }

    public ParamException(Error error) {
        super(error);
    }

    public ParamException(String message) {
        super(new Error(PARAM_ERROR.getCode(), message));
    }

}
