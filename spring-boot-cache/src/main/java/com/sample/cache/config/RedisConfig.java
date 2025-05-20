package com.sample.cache.config;

import com.sample.cache.constant.CacheConstants;
import com.sample.cache.listener.RedisSubscriberListener;
import com.sample.cache.listener.TestSubscriberListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: kiturone
 * @date: 2019/7/31 10:22
 * @version：1.0.0
 * @description:
 */
@Configuration
public class RedisConfig {

    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
        stringRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setHashValueSerializer(new StringRedisSerializer());
        stringRedisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return stringRedisTemplate;
    }

    @Bean
    public MessageListenerAdapter redisListenerAdapter(RedisSubscriberListener listener) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
        adapter.setSerializer(new StringRedisSerializer());
        adapter.afterPropertiesSet();
        return adapter;
    }

    @Bean
    public MessageListenerAdapter testListenerAdapter(TestSubscriberListener listener) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
        adapter.setSerializer(new StringRedisSerializer());
        adapter.afterPropertiesSet();
        return adapter;
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter redisListenerAdapter,
                                                   MessageListenerAdapter testListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //指定监听的对象
        container.addMessageListener(redisListenerAdapter, new PatternTopic(CacheConstants.REDIS_PUB_SUB_CHANNEL));
        container.addMessageListener(testListenerAdapter, new PatternTopic(CacheConstants.REDIS_TEST_CHANNEL));
        return container;
    }
}
