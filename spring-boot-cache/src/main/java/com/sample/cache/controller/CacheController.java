package com.sample.cache.controller;

import com.alibaba.fastjson.JSON;
import com.sample.cache.domain.User;
import com.sample.cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lingjun.jlj
 * @date: 2019/12/1 19:30
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping(value = "/test/{id}")
    public String cache(@PathVariable("id") Long id){
        User user  = cacheService.findUserById(id);
        log.info("查询日志消息：{}", JSON.toJSONString(user));
        return "查询成功";

    }
}
