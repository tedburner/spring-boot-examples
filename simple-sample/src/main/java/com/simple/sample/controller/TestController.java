package com.simple.sample.controller;

import com.simple.sample.service.common.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lingjun.jlj
 * @date: 2019/11/27 10:27
 * @description:
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/say")
    public String sayHello() {
        testService.sayHello();
        return "成功";
    }

    @GetMapping(value = "/say/async")
    public String asyncSayHello() {
        testService.sayWaitHello();
        return "成功";
    }
}
