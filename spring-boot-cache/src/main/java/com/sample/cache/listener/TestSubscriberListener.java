package com.sample.cache.listener;

import com.sample.cache.constant.CacheConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: kiturone
 * @date: 2020/5/27 21:04
 * @description: Redis 订阅发布监听类
 */
@Slf4j
@Component
public class TestSubscriberListener implements MessageListener {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public TestSubscriberListener(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("Redis 订阅通道: {}，监听到的消息：{}", CacheConstants.REDIS_TEST_CHANNEL, message);
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        String msg = stringRedisTemplate.getStringSerializer().deserialize(body);
        String topic = stringRedisTemplate.getStringSerializer().deserialize(channel);
        log.info("监听到 topic 为：{} ，消息：{}, patter: {}", topic, msg, new String(pattern));
    }
}
