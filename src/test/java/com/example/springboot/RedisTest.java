package com.example.springboot;

import com.example.springboot.domain.DO.CityDO;
import com.example.springboot.service.common.CityService;
import com.example.springboot.utils.redis.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Lucifer
 * @data 2018/4/28
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private CityService cityService;

    @Test
    public void redisTest(){
        CityDO cityDO = cityService.getCityById(1L);
        redisUtils.set("city_1",cityDO);
        CityDO redisValue =(CityDO) redisUtils.get("city_1");
        System.out.println(redisValue);
    }

    @Test
    public void test(){
        redisUtils.set("a","abc_name");
        String redisValue =(String) redisUtils.get("a");
        System.out.println(redisValue);
    }
}
