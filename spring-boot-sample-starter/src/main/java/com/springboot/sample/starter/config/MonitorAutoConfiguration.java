package com.springboot.sample.starter.config;

import com.springboot.sample.starter.properties.MonitorProperties;
import com.springboot.sample.starter.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author: lingjun.jlj
 * @date: 2021/3/20 21:45
 * @description: 自动化装配类，初始化 monitor boot starter
 */
@Configurable
@ConditionalOnBean(SampleMarkerConfiguration.Marker.class)
@EnableConfigurationProperties(MonitorProperties.class)
public class MonitorAutoConfiguration {

    @Autowired
    private MonitorProperties monitorProperties;

    @Bean
    @ConditionalOnMissingBean
    public MonitorService monitorService() {
        return new MonitorService(monitorProperties);
    }
}
