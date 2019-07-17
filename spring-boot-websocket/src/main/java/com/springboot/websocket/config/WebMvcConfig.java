package com.springboot.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: lingjun.jlj
 * @date: 2019/7/4 10:44
 * @version：1.0
 * @description:
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ws01")
                .setViewName("/ws01");
        registry.addViewController("/ws02")
                .setViewName("/ws02");
    }
}
