package com.sample.cache.service.impl;

import com.sample.cache.domain.Task;
import com.sample.cache.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

/**
 * @author: lingjun.jlj
 * @date: 2019/7/31 10:25
 * @version：1.0.0
 * @description:
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String QUEUE_NAME = "delay_queue";

    @Override
    public void addDelayQueue(Task task) {
    }
}
