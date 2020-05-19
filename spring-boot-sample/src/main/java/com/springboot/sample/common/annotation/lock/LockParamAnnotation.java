package com.springboot.sample.common.annotation.lock;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: lingjun.jlj
 * @date: 2018-12-07 10:26
 * @description: 用于参数的分布式锁注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Documented
public @interface LockParamAnnotation {

    /**
     * 参数的域，用于表明参数的业务场景，例如orderSn等
     *
     * @return
     */
    String value() default "";
}
