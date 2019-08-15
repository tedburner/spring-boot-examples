package com.simple.sample.controller;

import com.simple.sample.exception.BaseException;
import com.simple.sample.exception.ParamException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lingjun.jlj
 * @date: 2019/8/15 10:44
 * @description: 测试全局异常捕捉
 */
@RestController
public class ExceptionController {

    @RequestMapping("/ex1")
    public Object throwBaseException() throws Exception {
        throw new BaseException("This is BaseException.");
    }

    @RequestMapping("/ex2")
    public Object throwParamException() throws Exception {
        throw new ParamException("This is ParamException.");
    }

    @RequestMapping("/ex3")
    public Object throwException() throws Exception {
        throw new Exception("This is Exception.");
    }

    @RequestMapping("/ex4")
    public Object throwNullPointerException() throws Exception {
        throw new NullPointerException("This is NullPointerException.");
    }

}
