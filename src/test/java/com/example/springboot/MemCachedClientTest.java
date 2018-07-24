package com.example.springboot;

import com.example.springboot.utils.memcached.MemcachedUtils;
import com.whalin.MemCached.MemCachedClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/24 14:47
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemCachedClientTest {

    @Test
    public void MemCachedTest() {
        MemcachedUtils.set("memcache","123");
        System.out.println(MemcachedUtils.get("memcache"));
    }
}
