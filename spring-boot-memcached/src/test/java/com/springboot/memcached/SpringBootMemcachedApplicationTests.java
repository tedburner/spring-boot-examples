package com.springboot.memcached;

import com.springboot.memcached.runner.MemcachedRunner;
import net.spy.memcached.MemcachedClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMemcachedApplicationTests {

    @Autowired
    private MemcachedRunner memcachedRunner;

    @Test
    public void contextLoads() {
        MemcachedClient memcachedClient = memcachedRunner.getClient();

        memcachedClient.set("testkey", 1000, "666666");

        System.out.println("******** " + memcachedClient.get("testkey").toString() + " **********");
    }

}

