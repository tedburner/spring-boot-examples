package com.simple.sample.service.say;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author: kiturone
 * @date: 2020/8/19 12:01
 * @description:
 */
@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SayHello extends SayService {

    @Override
    public void say() {
        super.say();
        log.info("Hello");
    }
}
