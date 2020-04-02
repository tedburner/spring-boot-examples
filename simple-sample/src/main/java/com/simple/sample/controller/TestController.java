package com.simple.sample.controller;

import com.simple.sample.domain.dto.UserDTO;
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

    @GetMapping(value = "/aop")
    public String aopTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setUsername("张三");
        userDTO.setPassword("密码");
        userDTO.setCardNo("330781187609092330");
        testService.aspectCase(userDTO);
        return "成功";
    }
}
