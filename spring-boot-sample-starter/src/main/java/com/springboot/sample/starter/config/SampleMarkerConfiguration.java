package com.springboot.sample.starter.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author: kiturone
 * @date: 2021/4/7 11:34
 * @description: active sample starter server
 * {@link MonitorAutoConfiguration}
 */
@Configurable
public class SampleMarkerConfiguration {

    @Bean
    public Marker sampleServerMarkerBean() {
        return new Marker();
    }

    class Marker {

    }
}
