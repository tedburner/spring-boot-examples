package com.sample.springboot.common.aop.lock;

import com.google.common.collect.Lists;
import com.kit.common.cache.CacheClient;
import com.sample.springboot.common.annotation.lock.LockAnnotation;
import com.sample.springboot.common.annotation.lock.LockParamAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author: Lucifer
 * @date: 2018-12-07 10:30
 * @description:
 */
@Slf4j
@Aspect
@Component
public class LockAspect {

    @Autowired
    private CacheClient cacheClient;

    /**
     * lua 脚本 删除 Redis Lock
     */
    private static final String script = "if redis.call('get', KEYS[1]) == ARGV[1] " +
            "then " +
            "return redis.call('del', KEYS[1]) " +
            "else " +
            "return 0 " +
            "end;";

    @Pointcut("@annotation(com.sample.springboot.common.annotation.lock.LockAnnotation)")
    public void redisLockPointcut() {

    }

    @Around(value = "redisLockPointcut()")
    public Object redisLockAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取要处理的对象的类
        Class targetClass = joinPoint.getTarget().getClass();
        //获取对象的所有方法
        Method[] methods = targetClass.getMethods();
        //获取参数
        Object[] args = joinPoint.getArgs();
        //获取签名
        Signature signature = joinPoint.getSignature();
        Optional<Method> optional = Lists.newArrayList(methods).stream()
                .filter(m -> Objects.equals(m.getName(), signature.getName()))
                .findFirst();
        Object returnObject = null;
        List<String> annotationParamList = Lists.newArrayList();
        if (optional.isPresent()) {
            Method method = optional.get();
            LockAnnotation lockAnnotation = method.getAnnotation(LockAnnotation.class);
            Annotation[][] paramAnnotations = method.getParameterAnnotations();
            final int[] index = {0};
            Lists.newArrayList(paramAnnotations).forEach(param -> {
                Optional annotationOptional = Lists.newArrayList(param).stream()
                        .filter(m -> Objects.equals(m.annotationType(), LockParamAnnotation.class))
                        .findFirst();
                if (annotationOptional.isPresent()) {
                    LockParamAnnotation lockParamAnnotation = (LockParamAnnotation) annotationOptional.get();
                    annotationParamList.add(lockParamAnnotation.value() + ":" + args[index[0]].toString());
                    return;
                }
                index[0]++;
            });
            String lockParams = String.join("#", annotationParamList);
            String lockField = lockAnnotation.lockField();
            int lockExpireTime = lockAnnotation.lockExpireTime();
            StringBuffer lockKey = new StringBuffer(lockField)
                    .append(":")
                    .append(lockParams);
            String randomValue = UUID.randomUUID().toString();
            try {
                log.info("redis loc key : {}", lockKey.toString());
                if (cacheClient.setnx(lockKey.toString(), randomValue, lockExpireTime) != null) {
                    if (log.isInfoEnabled()) {
                        log.info("无法获得锁,方法名:{},参数为:{}", joinPoint.getSignature(), args);
                    }
                    return null;
                }
                if (log.isInfoEnabled()) {
                    log.info("可以获得锁,方法名为{},参数为{}", joinPoint.getSignature(), args);
                }
                // 执行方法
                returnObject = joinPoint.proceed(args);
            } finally {
                //使用 Lua 脚本执行 Redis 操作，保证原子性
                //cacheClient.eval(script, lockKey.toString(), randomValue);
            }
        }
        return returnObject;
    }

}
