package com.example.springboot.config;

import com.example.springboot.interceptor.HttpInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author lingjun.jlj
 * @data 2018/5/4
 * @Description: 拦截器
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        //注册自定义拦截器，添加拦截路径和排除拦截路径
//        registry.addInterceptor(new HttpInterceptor())
//                .addPathPatterns("api/path/**") //指定拦截器类
//                .excludePathPatterns("api/path/login"); //指定该类拦截的url
    }
}
