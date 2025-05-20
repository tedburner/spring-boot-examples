package com.springboot.sample.starter.annotation;

import com.springboot.sample.starter.config.SampleMarkerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: kiturone
 * @date: 2021/4/7 11:33
 * @description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SampleMarkerConfiguration.class)
public @interface EnableSampleServer {
}
