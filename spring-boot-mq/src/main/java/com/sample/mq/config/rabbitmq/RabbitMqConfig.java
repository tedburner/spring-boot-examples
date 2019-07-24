package com.sample.mq.config.rabbitmq;

import com.sample.mq.constant.MqConstants;
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


    /**
     * 绑定消息延迟队列和交换机
     */
    @Bean
    public Binding dlxTestDemoBinding() {
        return BindingBuilder.bind(queueConfig.delayQueueExpire())
                .to(exchangeConfig.delayQueueExpireExchange())
                .with(MqConstants.QUEUE_QUEUE_EXPIRE_DELAY);
    }

    /**
     * 绑定实际队列和交换器
     *
     * @return
     */
    @Bean
    public Binding processTestDemoBinding() {
        return BindingBuilder.bind(queueConfig.processQueueExpire())
                .to(exchangeConfig.processQueueExpireExchange())
                .with(MqConstants.QUEUE_QUEUE_EXPIRE_PROCESS);
    }


    /**
     * 绑定消息延迟队列和交换机
     */
    @Bean
    public Binding dlxMsgExpireBinding() {
        return BindingBuilder.bind(queueConfig.delayMsgExpireQueue())
                .to(exchangeConfig.delayMsgExpireExchange())
                .with(MqConstants.QUEUE_MSG_EXPIRE);
    }

    /**
     * 消息过期绑定
     *
     * @return
     */
    @Bean
    public Binding processMsgExpireBinding() {
        return BindingBuilder.bind(queueConfig.processMsgExpireQueue())
                .to(exchangeConfig.processMsgExpireExchange())
                .with(MqConstants.QUEUE_MSG_EXPIRE_PROCESS);
    }


}
