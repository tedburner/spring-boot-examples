package com.springboot.sample.config;

import com.springboot.sample.interceptors.HttpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author: lingjun.jlj
 * @date: 2018/7/26 17:56
 * @description: 拦截器配置
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    private final HttpInterceptor httpInterceptor;


    @Autowired
    public InterceptorConfig(HttpInterceptor httpInterceptor) {
        this.httpInterceptor = httpInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        //addPathPatterns 用于添加拦截规则
        //excludePathPatterns 用于排除拦截
        registry.addInterceptor(httpInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login/**")
                .order(1);
    }
}
