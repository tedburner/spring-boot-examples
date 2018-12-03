package com.kit.common.util.exception;

import com.kit.common.util.exception.domain.Error;
import com.kit.common.util.http.ResponseCodeEnum;

/**
 * @author: Lucifer
 * @date: 2018/8/15 15:21
 * @description: 参数错误异常
 */
public class ParamException extends ApplicationException {

    private static final long serialVersionUID = -1500549226584891801L;

    public ParamException() {
        super(Error.builder()
                .code(ResponseCodeEnum.PARAM_ERROR.getCode())
                .message(ResponseCodeEnum.PARAM_ERROR.getMessage())
                .build());
    }

    public ParamException(Error error) {
        super(error);
    }

    public ParamException(String message) {
        super(new Error(ResponseCodeEnum.PARAM_ERROR.getCode(), message));
    }

}
