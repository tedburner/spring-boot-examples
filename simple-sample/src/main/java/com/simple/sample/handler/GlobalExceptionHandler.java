package com.simple.sample.handler;

import com.simple.sample.exception.BaseException;
import com.simple.sample.exception.ParamException;
import com.simple.sample.util.http.NewResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author: lingjun.jlj
 * @date: 2019/8/15 10:43
 * @description:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = BaseException.class)
    public Object baseErrorHandler(BaseException exception, HttpServletRequest request, HttpServletResponse response) {
        String url = request.getRequestURL().toString();
        log.error("请求：[{}],发生自定义【BaseException】异常：[{}]-[{}]", url, exception.getCode(), exception.getMessage());
        if (exception instanceof ParamException) {
            ParamException paramException = (ParamException) exception;
            return new NewResponseModel(paramException.getCode(), paramException.getMessage());
        }else {
            return NewResponseModel.Fail();
        }
    }

    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("---DefaultException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return e.getMessage();
    }
}
