package com.simple.sample;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: lingjun.jlj
 * @date: 2021/4/19 11:03
 * @description:
 */
@Slf4j
@SpringBootTest
public class RedissonTests {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void redissonTest() {
        RBucket<String> key = redissonClient.getBucket("newday");
        key.set("new data");
        log.info("get new data: {}", key.get());
        RBucket<String> keyObj = redissonClient.getBucket("myname");
        String s = keyObj.get();
        log.info("get old data:{}", s);
    }
}
