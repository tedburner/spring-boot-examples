package com.example.SpringBoot.aop;

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

    @Before("execution(* com.example.SpringBoot.service..*.*(..))")
    public void before(){
        //System.out.println("Spring AOP");
    }
}
