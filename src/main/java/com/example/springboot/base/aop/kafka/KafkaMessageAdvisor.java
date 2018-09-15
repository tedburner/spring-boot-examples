package com.example.springboot.base.aop.kafka;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: 蒋灵俊
 * @Date: 2018/6/11 16:04
 * @Description: kafka message aop切面
 */
@Deprecated
public class KafkaMessageAdvisor implements Advisor {

    @Autowired
    private KafkaMessageAroundAdvice advice;

    public KafkaMessageAdvisor() {
        this.advice = new KafkaMessageAroundAdvice();
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
