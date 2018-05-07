package com.example.SpringBoot;

import com.example.SpringBoot.utils.redis.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lingjun.jlj
 * @data 2018/4/28
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void redisTest(){
        //redisUtils.set("key","Hello world");
        //redisUtils.set("key1","2分钟失效",120L);
        String redisValue =(String) redisUtils.get("key1");
        System.out.println(redisValue);
    }
}
