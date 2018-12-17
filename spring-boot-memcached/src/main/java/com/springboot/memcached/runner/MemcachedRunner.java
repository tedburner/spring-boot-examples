package com.springboot.memcached.runner;

import com.springboot.memcached.config.MemcacheSource;
import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;


/**
 * @author: Lucifer
 * @date: 2018-12-17 10:11
 * @description:
 */
@Component
public class MemcachedRunner implements CommandLineRunner {

    @Autowired
    private MemcacheSource memcacheSource;

    private MemcachedClient client = null;

    @Override
    public void run(String... args) throws Exception {
        try {
            client = new MemcachedClient(new InetSocketAddress(memcacheSource.getHost(), memcacheSource.getPort()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MemcachedClient getClient() {
        return client;
    }
}
