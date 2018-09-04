package com.example.SpringBoot;

import com.example.springboot.model.DO.GoodDO;
import com.whalin.MemCached.MemCachedClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author lingjun.jlj
 * @date 2018-01-04
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemcachedTest {

    @Resource
    private MemCachedClient memCachedClient;

    @Test
    public void addMemCachedTest() {
        for (int i = 0; i < 10; i++) {
            memCachedClient.add("i", "memcached" + i);
        }

        System.out.println(memCachedClient.get("0"));
    }
}
