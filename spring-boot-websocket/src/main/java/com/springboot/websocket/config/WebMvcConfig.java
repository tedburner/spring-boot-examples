package com.springboot.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: lingjun.jlj
 * @date: 2019/7/4 10:44
 * @version：1.0
 * @description:
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ws")
                .setViewName("/ws");
        registry.addViewController("/ws1")
                .setViewName("/ws1");
        registry.addViewController("/index")
                .setViewName("/index");
    }
}
