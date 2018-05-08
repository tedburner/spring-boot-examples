package com.example.springboot.base.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author lingjun.jlj
 * @create 2017-11-30
 **/
@Aspect
@Component
public class ServiceAop {

    @Before("execution(* com.example.springboot.service..*.*(..))")
    public void before(){
        //System.out.println("Spring AOP");
    }
}
