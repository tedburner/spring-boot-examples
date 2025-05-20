package com.simple.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: kiturone
 * @date: 2019/12/4 20:27
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/tomcat")
public class TomcatThreadController {

    private static final ThreadLocal<String> local = new ThreadLocal<>();

    @GetMapping(value = "/thread/test1")
    public String threadTest1(){
        String msg =  local.get();
        if (StringUtils.isNotBlank(msg)){
            log.info("ThreadLocal Test1 缓存：{}", msg);
        }
        local.set("这是Test1插入的数据");
        return "操作成功";
    }

    @GetMapping(value = "/thread/test2")
    public String threadTest2(){
        String msg =  local.get();
        if (StringUtils.isNotBlank(msg)){
            log.info("ThreadLocal Test2 缓存：{}", msg);
        }
        local.set("这是Test2插入的数据");
        return "操作成功";
    }




}
