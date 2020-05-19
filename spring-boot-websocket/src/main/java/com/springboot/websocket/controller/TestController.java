package com.springboot.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lingjun.jlj
 * @date: 2019/9/4 20:59
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/msg")
    public String getMsg(){
        log.info("这是服务器1的数据");
        return "这是服务器1的数据";
    }
}
