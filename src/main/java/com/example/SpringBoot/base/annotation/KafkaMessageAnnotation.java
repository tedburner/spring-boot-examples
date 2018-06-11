package com.example.springboot.base.annotation;

import java.lang.annotation.*;

/**
 * @author: 蒋灵俊
 * @Date: 2018/6/11 15:57
 * @Description: kafka message annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface KafkaMessageAnnotation {
}
