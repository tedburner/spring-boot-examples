package com.sample.cache.controller;


import com.kit.common.util.common.gson.FormatUtils;
import com.sample.cache.constant.CacheConstants;
import com.sample.cache.domain.Task;
import com.sample.cache.domain.User;
import com.sample.cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: kiturone
 * @date: 2019/12/1 19:30
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/cache")
public class CacheController {

    private final CacheService cacheService;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public CacheController(CacheService cacheService, StringRedisTemplate stringRedisTemplate) {
        this.cacheService = cacheService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping(value = "/test/{id}")
    public String cache(@PathVariable("id") Long id) {
        User user = cacheService.findUserById(id);
        log.info("查询日志消息：{}", FormatUtils.obj2str(user));
        return "查询成功";
    }

    @GetMapping(value = "/pubsub")
    public String redisPubSub() {
        Task task = new Task();
        task.setId("1");
        task.setDesc("Redis订阅发布");
        task.setTime(System.currentTimeMillis());
        stringRedisTemplate.convertAndSend(CacheConstants.REDIS_PUB_SUB_CHANNEL, FormatUtils.obj2str(task));
        log.info("通道号{}发布成功", CacheConstants.REDIS_PUB_SUB_CHANNEL);

        Task task1 = new Task();
        task1.setId("2");
        task1.setDesc("Redis订阅发布测试多订阅通道");
        task1.setTime(System.currentTimeMillis());
        stringRedisTemplate.convertAndSend(CacheConstants.REDIS_TEST_CHANNEL, FormatUtils.obj2str(task1));
        log.info("通道号{}发布成功", CacheConstants.REDIS_TEST_CHANNEL);
        return "查询成功";
    }
}
