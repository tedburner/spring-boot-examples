package com.sample.mq.config.rabbitmq;

import com.sample.rabbitmq.constant.MqConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Arthas
 * @date: 2019-07-19 23:05
 * @description: RabbitMQ 绑定 交换机和队列
 */
@Configuration
public class RabbitMqConfig {

    @Autowired
    private ExchangeConfig exchangeConfig;
    @Autowired
    private QueueConfig queueConfig;

    @Bean
    public Binding testBinding() {
        return BindingBuilder.bind(queueConfig.testQueue())
                .to(exchangeConfig.testExchange())
                .with(MqConstants.QUEUE_TEST);
    }
}
