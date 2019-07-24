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
     * 测试延迟队列的延迟队列交换机
     */
    @Bean
    public DirectExchange delayTestDemoExchange() {
        return new DirectExchange(MqConstants.EXCHANGE_TEST_DEMO_DELAY);
    }

    /**
     * 测试延迟队列的实际处理队列交换机
     */
    @Bean
    public DirectExchange processTestDemoExchange() {
        return new DirectExchange(MqConstants.EXCHANGE_TEST_DEMO_PROCESS);
    }
}
