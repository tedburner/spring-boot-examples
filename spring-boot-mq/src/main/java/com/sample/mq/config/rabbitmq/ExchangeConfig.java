package com.sample.mq.config.rabbitmq;

import com.sample.rabbitmq.constant.MqConstants;
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
}
