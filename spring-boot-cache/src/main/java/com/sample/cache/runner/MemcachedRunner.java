package com.sample.cache.runner;

import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;


/**
 * @author: kiturone
 * @date: 2018-12-17 10:11
 * @description:
 */
@Component
public class MemcachedRunner implements CommandLineRunner {

//    @Value("${memcache.host}")
//    private String host;
//
//    @Value("${memcache.port}")
//    private int port;

//    private MemcachedClient client = null;

    @Override
    public void run(String... args) throws Exception {
//        try {
//            client = new MemcachedClient(new InetSocketAddress(host, port));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

//    public MemcachedClient getClient() {
//        return client;
//    }
}
