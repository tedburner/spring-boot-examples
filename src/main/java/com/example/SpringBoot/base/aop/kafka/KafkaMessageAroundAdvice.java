package com.example.springboot.base.aop.kafka;

import com.example.springboot.persist.kafkaMessage.KafkaMessageMapper;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author: 蒋灵俊
 * @Date: 2018/6/11 16:05
 * @Description:
 */
public class KafkaMessageAroundAdvice implements Advice,
        MethodInterceptor, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Autowired
    private KafkaMessageMapper kafkaMessageMapper;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        KafkaMessageAroundAdvice.applicationContext = applicationContext;
    }
}
