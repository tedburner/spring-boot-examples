package com.example.springboot.config;

import com.example.springboot.interceptor.HttpInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        //addPathPatterns 用于添加拦截规则
        //excludePathPatterns 用于排除拦截
        registry.addInterceptor(new HttpInterceptor())
                .addPathPatterns("api/**")
                .excludePathPatterns("api/login");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/web/**").
                allowedOrigins("*").
                allowedMethods("POST", "GET").
                allowedHeaders("Origin, X-Requested-With, Content-Type, Accept").
                allowCredentials(true).
                maxAge(3600);
        super.addCorsMappings(registry);
    }
}
