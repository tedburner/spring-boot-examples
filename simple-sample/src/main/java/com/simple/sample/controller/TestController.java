package com.simple.sample.controller;

import com.simple.sample.aspect.annotation.RateLimit;
import com.simple.sample.aspect.annotation.RedisLock;
import com.simple.sample.domain.dto.UserDTO;
import com.simple.sample.service.common.TestService;
import com.simple.sample.service.say.SayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: lingjun.jlj
 * @date: 2019/11/27 10:27
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/test")
public class TestController {

    private final TestService testService;
    @Autowired
    private List<SayService> sayServiceList;

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
    @RedisLock(key = "#id")
    public String aopTest(String id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setUsername("张三");
        userDTO.setPassword("密码");
        userDTO.setCardNo("330781187609092330");
        testService.aspectCase(userDTO);
        return "成功";
    }

    @GetMapping(value = "/synchronized/{name}")
    public String synchronizedTest(@PathVariable(value = "name") String name) {
        testService.testSynchronizedChar(name);
        return "成功";
    }

    @GetMapping(value = "test")
    public void test() {
        log.info("====================");
        sayServiceList.forEach(SayService::say);
    }

    @GetMapping(value = "/flow")
    @RateLimit
    public String rateLimit() {
        return "成功！";
    }
}
