package com.sample.mq.config.rabbitmq;

import com.sample.rabbitmq.constant.MqConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
