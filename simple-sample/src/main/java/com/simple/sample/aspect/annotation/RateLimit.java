package com.simple.sample.aspect.annotation;

import java.lang.annotation.*;

/**
 * @author: kiturone
 * @date: 2021/5/15 12:55
 * @description:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RateLimit {
}
