package com.simple.sample.config;

import com.simple.sample.interceptors.HttpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author: lingjun.jlj
 * @date: 2021/5/15 12:37
 * @description:
 */
@Configurable
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private HttpInterceptor httpInterceptor;


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
