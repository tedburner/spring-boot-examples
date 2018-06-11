package com.example.springboot.base.aop.kafka;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;

/**
 * @author: 蒋灵俊
 * @Date: 2018/6/11 16:04
 * @Description: kafka message aop切面
 */
public class KafkaMessageAdvisor implements Advisor {

    @Override
    public Advice getAdvice() {
        return null;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
