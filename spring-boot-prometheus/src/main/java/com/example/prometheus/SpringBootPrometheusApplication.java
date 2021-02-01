package com.example.prometheus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: lingjun.jlj
 * @description: prometheus 监控服务器指标
 * */
@SpringBootApplication
public class SpringBootPrometheusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPrometheusApplication.class, args);
    }

}
