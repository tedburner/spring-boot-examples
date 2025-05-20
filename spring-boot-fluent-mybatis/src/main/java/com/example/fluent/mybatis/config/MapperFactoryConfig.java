package com.example.fluent.mybatis.config;

import cn.org.atool.fluent.mybatis.spring.MapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: kiturone
 * @date: 2022/3/23 23:12
 * @description:
 */
@Configuration
public class MapperFactoryConfig {

    @Bean
    public MapperFactory mapperFactory() {
        return new MapperFactory();
    }
}
