package com.springboot.sample.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author: kiturone
 * @date: 2018/11/15 09:44
 * @description:
 */
@Data
@Component
//@ConfigurationProperties(prefix = "aliyun")
//@PropertySource(value = "classpath:config.properties", encoding = "utf-8")
public class AliyunProperties {

    private String accessKeyID;
    private String accessKeySecret;
    private String signName;

}
