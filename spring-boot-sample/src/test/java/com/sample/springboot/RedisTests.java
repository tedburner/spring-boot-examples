package com.sample.springboot;

import com.kit.common.cache.RedisCacheClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: Lucifer
 * @date: 2018-11-30 14:11
 * @description:
 */
public class RedisTests extends SpringBootSampleTests {

    @Autowired
    private RedisCacheClient redisCacheClient;

    @Test
    public void RedisTest() {

        redisCacheClient.set("Test", "key", "today, is Friday");

        System.out.println((String) redisCacheClient.get("Test", "key"));

    }
}