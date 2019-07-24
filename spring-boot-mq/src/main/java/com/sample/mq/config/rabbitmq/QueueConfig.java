package com.sample.mq.config.rabbitmq;

import com.sample.mq.constant.MqConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Arthas
 * @date: 2019-07-19 23:05
 * @description: 队列配置
 */
@Configuration
public class QueueConfig {

    @Bean
    public Queue testQueue() {
        return new Queue(MqConstants.QUEUE_TEST, false, false, true);
    }

    /**
     * 延迟队列设置
     */
    @Bean
    public Queue delayTestDemo() {
        Map<String, Object> params = new HashMap<>(4);
        // 消息到时候发送到该指定exchange
        params.put("x-dead-letter-exchange", MqConstants.EXCHANGE_TEST_DEMO_PROCESS);
        // 发送到该exchange指定的路由
        params.put("x-dead-letter-routing-key", MqConstants.QUEUE_TEST_DEMO_PROCESS);
        // 消息的延迟时间
        params.put("x-message-ttl", MqConstants.TEST_DEMO_TIMEOUT);
        //控制 queue 被自动删除前可以处于未使用状态的时间,20s
//        params.put("x-expires", 20 * 1000);
        return new Queue(MqConstants.QUEUE_TEST_DEMO_DELAY, true, false, false, params);
    }

    /**
     * 延迟队列实际处理队列
     *
     * @return
     */
    @Bean
    public Queue processTestDemo() {
        return QueueBuilder
                .durable(MqConstants.QUEUE_TEST_DEMO_PROCESS)
                .build();
    }
}
