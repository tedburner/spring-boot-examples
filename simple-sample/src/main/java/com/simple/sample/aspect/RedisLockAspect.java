package com.simple.sample.aspect;

import com.simple.sample.aspect.annotation.RedisLock;
import com.simple.sample.constant.GlobalConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author: kiturone
 * @date: 2021/4/28 16:16
 * @description:
 */
@Slf4j
@Aspect
@Component
public class RedisLockAspect {

    @Autowired
    private RedissonClient redissonClient;

    @Pointcut("@annotation(com.simple.sample.aspect.annotation.RedisLock)")
    public void redisLock() {
    }

    @Around(value = "redisLock()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        //获取签名
        RedisLock redisLock = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(RedisLock.class);
        // 获取锁的参数
        String key = this.parseKey(redisLock.key(), joinPoint);
        long time = redisLock.time();
        TimeUnit unit = redisLock.unit();
        String lockKey = GlobalConstants.REDIS_LOCK + key;
        RLock lock = redissonClient.getLock(lockKey);
        try {
            boolean res = lock.tryLock(time, unit);

            if (!res) {
                log.error("请勿重复提交，key = {}", lockKey);
                throw new RuntimeException("请勿重复提交");
            }
            return joinPoint.proceed(args);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 解析SpEL表达式
     *
     * @param key 需要被解析的 key
     * @param point
     * @return
     */
    private String parseKey(String key, ProceedingJoinPoint point) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        // 如果不是以#开头的，不需要 SpEL进行解析
        if (!key.startsWith(GlobalConstants.COMMA_POUND_SIGN_CHAR)) {
            return key;
        }
        // 通过joinPoint获取被注解方法
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();
        // 使用spring的DefaultParameterNameDiscoverer获取方法形参名数组
        String[] paraNameArr = nameDiscoverer.getParameterNames(method);
        SpelExpressionParser parser = new SpelExpressionParser();
        // 解析过后的Spring表达式对象
        Expression expression = parser.parseExpression(key);
        // spring的表达式上下文对象
        EvaluationContext context = new StandardEvaluationContext();
        // 通过joinPoint获取被注解方法的形参
        Object[] args = point.getArgs();
        if (paraNameArr != null) {
            // 给上下文赋值
            for (int i = 0; i < paraNameArr.length; i++) {
                context.setVariable(paraNameArr[i], args[i]);
            }
        }
        return expression.getValue(context, String.class);
    }
}
