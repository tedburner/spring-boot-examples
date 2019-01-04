package com.springboot.ehcache;

import com.springboot.ehcache.service.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EhcacheApplicationTests {

    @Autowired
    private CacheService cacheService;

    @Test
    public void contextLoads() {

        System.out.println(cacheService.findUserById(1L));
    }

}

