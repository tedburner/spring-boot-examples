package com.springboot.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lingjun.jlj
 * @date: 2020/12/7 15:49
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "test")
public class TestController {

    @RequestMapping(value = "json")
    public String json(String json) {
        return json;
    }
}
