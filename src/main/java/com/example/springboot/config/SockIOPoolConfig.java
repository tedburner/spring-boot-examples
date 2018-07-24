package com.example.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/24 14:39
 * @Description:
 */
@Component
@PropertySource(value = "classpath:memcached/memcached.properties")
public class SockIOPoolConfig {
}
