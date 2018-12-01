package com.sample.springboot.config;

import com.kit.common.redis.KitShardedJedisPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Lucifer
 * @date: 2018/8/20 10:05
 * @description:
 */
@Slf4j
@Configuration
@PropertySource(value = "classpath:redis/redis.properties")
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${redis.pool.max-idle}")
    private int maxIdle;

    @Value("${redis.pool.min-idle}")
    private int minIdle;

    @Value("${redis.timeout}")
    private int timeout;

    @Value("${redis.pool.max-active}")
    private int maxTotal;

    @Value("${redis.pool.max-wait}")
    private int maxWait;

    @Value("${redis.database}")
    private int database;

    @Value("${redis.master.host}")
    private String master_host;

    @Value("${redis.master.password}")
    private String master_password;

    @Value("${redis.slave.host}")
    private String slave_host;

    @Value("${redis.slave.password}")
    private String slave_password;

    @Value("${redis.port}")
    private int port;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setBlockWhenExhausted(true);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setFairness(false);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        return jedisPoolConfig;
    }


    @Bean
    public KitShardedJedisPool shardedJedisPool() {
        JedisShardInfo master = new JedisShardInfo(master_host, port, timeout);
        master.setPassword(master_password);

        JedisShardInfo slave = new JedisShardInfo(slave_host, port, timeout);
        slave.setPassword(slave_password);

        log.info("初始化 ShardedJedisPool");
        List<JedisShardInfo> jedisShardInfoList = Arrays.asList(master, slave);
        return new KitShardedJedisPool(jedisPoolConfig(), jedisShardInfoList);
    }

}
