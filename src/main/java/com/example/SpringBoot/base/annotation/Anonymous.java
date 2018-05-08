package com.example.SpringBoot.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lingjun.jlj
 * @data 2018/5/8
 * @Description: 【Controller访问控制，游客模式访问】
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Anonymous {
    boolean value() default false;
}