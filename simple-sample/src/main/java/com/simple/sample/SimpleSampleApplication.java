package com.simple.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author: Arthas
 * @description: spring boot 简单项目实例
 */
@EnableJpaAuditing
@SpringBootApplication
public class SimpleSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleSampleApplication.class, args);
    }

}
