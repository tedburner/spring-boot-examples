package com.example.springboot.base.aop;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author lingjun.jlj
 * @create 2017-11-30
 * @Description: 切面test
 **/
@Aspect
@Component
@Slf4j
public class ServiceAop {

    /**
     * 在service执行前打印log
     */
    @Before("execution(* com.example.springboot.service..*.*(..))")
    public void before() {
        log.info("准备执行service！！！");
    }
}
