package com.sample.mq.config.rabbitmq;

import com.sample.mq.constant.MqConstants;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Arthas
 * @date: 2019-07-19 23:05
 * @description: 转换机配置
 */
@Configuration
public class ExchangeConfig {

    @Bean
    public DirectExchange testExchange() {
        return new DirectExchange(MqConstants.EXCHANGE_TEST);
    }


    /**
     * 消息过期设置延迟队列
     *
     * @return
     */
    @Bean
    public DirectExchange delayMsgExpireExchange() {
        return new DirectExchange(MqConstants.EXCHANGE_MSG_EXPIRE);
    }

    /**
     * 消息过期设置实际处理队列
     *
     * @return
     */
    @Bean
    public DirectExchange processMsgExpireExchange() {
        return new DirectExchange(MqConstants.EXCHANGE_MSG_EXPIRE_PROCESS);
    }

    /**
     * 测试延迟队列的延迟队列交换机
     *
     * @return
     */
    @Bean
    public DirectExchange delayQueueExpireExchange() {
        return new DirectExchange(MqConstants.EXCHANGE_QUEUE_EXPIRE_DELAY);
    }

    /**
     * 测试延迟队列的实际处理队列交换机
     *
     * @return
     */
    @Bean
    public DirectExchange processQueueExpireExchange() {
        return new DirectExchange(MqConstants.EXCHANGE_QUEUE_EXPIRE_PROCESS);
    }

    /**
     * 优先级队列交换器
     *
     * @return
     */
    @Bean
    public DirectExchange priorityExchange() {
        return new DirectExchange(MqConstants.EXCHANGE_PRIORITY);
    }
}
