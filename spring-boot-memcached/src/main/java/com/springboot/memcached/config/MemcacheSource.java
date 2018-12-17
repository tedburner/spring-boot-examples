package com.springboot.memcached.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: Lucifer
 * @date: 2018-12-17 10:09
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "memcache")
public class MemcacheSource {

    private String host;

    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
