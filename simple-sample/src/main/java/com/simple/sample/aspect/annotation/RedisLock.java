package com.simple.sample.aspect.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author: lingjun.jlj
 * @date: 2021/4/28 16:15
 * @description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface RedisLock {

    /**
     * key
     * 注意：如果是常量，不要#开头；如果使用的是方法参数，使用 #id 这种形式进行注入属性值
     */
    String key();

    /**
     * 过期时间，默认1000毫秒
     */
    long time() default 1000L;

    /**
     * 缓存过期时间单位，毫秒
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;
}
