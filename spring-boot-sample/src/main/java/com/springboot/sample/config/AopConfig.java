package com.springboot.sample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author: kiturone
 * @data 2018/4/28
 * @Description: 开启AOP
 */
@Configuration
@ComponentScan("com.springboot.sample.common.aop")
@EnableAspectJAutoProxy //开启AspectJ
public class AopConfig {
}
