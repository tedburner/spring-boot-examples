package com.example.SpringBoot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author lingjun.jlj
 * @data 2018/4/28
 * @Description: 开启AOP
 */
@Configuration
@ComponentScan("com.example.SpringBoot.aop")
@EnableAspectJAutoProxy
public class AopConfig {
}
