package com.springboot.sample.util.exception.advice;

import com.kit.common.util.http.NewResponseModel;
import com.springboot.sample.util.exception.ApplicationException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author: kiturone
 * @date: 2018/8/25 14:19
 * @description: 全局捕获异常，只要是RequestMapping注解上的异常都可以捕获
 */

@RestControllerAdvice
public class ErrorException extends ResponseEntityExceptionHandler {

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }


    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public NewResponseModel errorHandler(Exception ex) {
        NewResponseModel responseModel = NewResponseModel.Fail();
        responseModel.setInfo(ex.getMessage());
        return responseModel;
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ApplicationException.class)
    public NewResponseModel myErrorHandler(ApplicationException exception) {
        NewResponseModel responseModel = new NewResponseModel();
        responseModel.setStatus(exception.getError().getStatus());
        responseModel.setInfo(exception.getError().getMessage());
        return responseModel;
    }
}
