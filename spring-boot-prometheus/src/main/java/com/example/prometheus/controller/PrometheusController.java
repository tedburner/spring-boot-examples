package com.example.prometheus.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lingjun.jlj
 * @date: 2021/2/2 13:49
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/prometheus")
public class PrometheusController {

    @GetMapping(value = "/get")
    public String get() {
        try {
            Thread.sleep(10);
            log.info("do something, cost time 10ms.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("请求成功....");
        return "Success";
    }
}
