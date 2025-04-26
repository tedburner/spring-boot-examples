package com.springboot.sample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author: lingjun.jlj
 * @description: 启动类
 * <p>
 * 使用@ServletComponentScan 注解后，Servlet、Filter、Listener
 * 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，
 * 无需其他代码
 */
@ServletComponentScan
@SpringBootApplication
@MapperScan("com.springboot.sample.persist")
@ComponentScan(basePackages = {"com.kit.common", "com.springboot.sample"})
public class SpringBootSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSampleApplication.class, args);
    }
}
