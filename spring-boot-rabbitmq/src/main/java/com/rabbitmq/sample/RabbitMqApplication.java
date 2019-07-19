package com.rabbitmq.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Arthas
 * @decription: RabbitMq消息中间件 demo
 */
@SpringBootApplication
public class RabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class, args);
    }

}
