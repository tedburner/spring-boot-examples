package com.simple.sample.aspect;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: kiturone
 * @date: 2021/5/15 12:56
 * @description:
 */
@Slf4j
@Aspect
@Component
public class RateLimitAspect {

    private final static RateLimiter rateLimiter = RateLimiter.create(5.0);

    @Pointcut("@annotation(com.simple.sample.aspect.annotation.RateLimit)")
    public void rateLimit() {
    }

    @Around(value = "rateLimit()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        boolean permit = rateLimiter.tryAcquire();
        if (!permit) {
            log.error("Flow is big, Rate Limit.");
            return null;
        }
        return joinPoint.proceed(args);
    }
}
