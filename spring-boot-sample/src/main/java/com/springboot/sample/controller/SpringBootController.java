package com.springboot.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kiturone
 * @create 2017-09-01
 **/
@Slf4j
@RestController
@RequestMapping(value = "/api/")
public class SpringBootController {


    @GetMapping("spring/index")
    String springboot() {
        return "Hello Spring Boot!";
    }

    @GetMapping("login")
    String login() {
        return "You are login!";
    }

    @GetMapping("hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello " + name;
    }

    /**
     * 测试 @RequestParam 接收 post 请求json参数
     * 该方式不可行，json 参数解析不了
     */
    @PostMapping("test")
    public String test(@RequestParam("id") Long id) {
        log.info("RequestParam" + id);
        return "RequestParam" + id;
    }
}
