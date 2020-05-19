package com.sample.flink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 搭建基于Flink 的流式计算平台
 * @author: lingjun.jlj
 * */
@SpringBootApplication
public class FlinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlinkApplication.class, args);
    }

}
