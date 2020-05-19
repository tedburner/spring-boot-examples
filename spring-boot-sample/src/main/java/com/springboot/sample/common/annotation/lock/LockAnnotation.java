package com.springboot.sample.common.annotation.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: lingjun.jlj
 * @date: 2018-12-07 10:26
 * @description: 用于方法的分布式锁注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface LockAnnotation {

    /**
     * 加锁得到key的域，用于前缀识别
     *
     * @return
     */
    String lockField() default "";

    /**
     * 锁自动释放时间
     */
    int lockExpireTime() default 600000;
}
