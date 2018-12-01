package com.sample.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;


/**
 * @author : Lucifer
 * @description: 启动类
 */
@EnableCaching
@EnableKafka
@ServletComponentScan
@SpringBootApplication
@EnableMongoRepositories
@MapperScan("com.sample.springboot.persist")
@ComponentScan("com.kit.common")
//@ImportResource("classpath:redis/spring_redis.xml")
public class SpringBootSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSampleApplication.class, args);
    }
}
