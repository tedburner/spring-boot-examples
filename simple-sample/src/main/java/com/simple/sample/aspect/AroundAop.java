package com.simple.sample.aspect;

import com.google.common.collect.Lists;
import com.simple.sample.aspect.annotation.AroundCase;
import com.simple.sample.aspect.annotation.FieldCase;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: lingjun.jlj
 * @date: 2020/4/2 20:33
 * @description:
 */
@Slf4j
@Aspect
@Component
public class AroundAop {

    @Pointcut("@annotation(com.simple.sample.aspect.annotation.AroundCase)")
    public void aroundCase() {

    }

    @Around(value = "aroundCase()")
    public void aroundCase(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取要处理的对象的类
        Class clazz = AopUtils.getTargetClass(joinPoint.getTarget());
        //获取对象的所有方法
        Method[] methods = clazz.getMethods();
        //获取参数
        Object[] args = joinPoint.getArgs();
        //获取签名
        Signature signature = joinPoint.getSignature();
        Optional<Method> optional = Lists.newArrayList(methods).stream()
                .filter(m -> Objects.equals(m.getName(), signature.getName()))
                .findFirst();
        if (optional.isPresent()) {
            Method method = optional.get();
            //获取注解上的参数
            AroundCase aroundCase = method.getAnnotation(AroundCase.class);
            log.info("参数：{}, {}, {}", aroundCase.name(), aroundCase.value(), aroundCase.parm());

            for (int i = 0; i < args.length; i++) {
                log.info("第" + (i + 1) + "个参数为:" + args[i]);
            }

            for (Object object : args) {
                Optional<Field> fieldOptional = Arrays.stream(object.getClass().getDeclaredFields())
                        .filter(x -> x.isAnnotationPresent(FieldCase.class))
                        .findFirst();

                if (fieldOptional.isPresent()) {
                    Field field = fieldOptional.get();
                    String fieldName = field.getName();
                    log.info("标有FieldCase注释的属性名称：{}", fieldName);
                    // 私有属性必须设置访问权限
                    field.setAccessible(true);
                    Object resultValue = field.get(object);
                    log.info("带有注释FieldCase 的属性值：{}", resultValue);
                }
            }

            log.info("行方法 ———————————— 开始");
            joinPoint.proceed(args);
            log.info("执行方法 ———————————— 结束");
        }
    }

}
