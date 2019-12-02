package com.sample.cache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.lang.reflect.Method;

/**
 * @author: lingjun.jlj
 * @date: 2019/7/31 10:22
 * @version：1.0.0
 * @description:
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

//    @Value("${spring.redis.host}")
//    private String masterHost;
//    @Value("${spring.redis.port}")
//    private String masterPort;
//    @Value("${spring.redis.password}")
//    private String masterPassword;
//    @Value("${spring.redis.sentinel.master}")
//    private String master;
//    @Value("${spring.redis.sentinel.nodes}")
//    private String redisNodes;

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /**
     * Redis 哨兵配置
     *
     * @return
     */
//    @Bean
//    public RedisSentinelConfiguration redisSentinelConfiguration() {
//        RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();
//        String[] hosts = redisNodes.split(",");
//        for (String host : hosts) {
//            String[] item = host.split(":");
//            String ip = item[0];
//            String port = item[1];
//            configuration.addSentinel(new RedisNode(ip, Integer.valueOf(port)));
//        }
//        configuration.setMaster(master);
//        return configuration;
//    }

//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
////构造方法中注入RedisSentinelConfiguration对象
//        JedisConnectionFactory factory = new JedisConnectionFactory(redisSentinelConfiguration());
//        factory.(host);
//        factory.setPort(port);
//        factory.setTimeout(timeout);
//        factory.setPassword(password);
//        factory.setDatabase(database);
//        return factory;
//    }
}
