package com.sample.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * @author: kiturone
 * @description: MQ 和spring boot 整合项目
 */

//@EnableKafka
@SpringBootApplication
@EnableConfigurationProperties
public class MqApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class, args);
    }

}
