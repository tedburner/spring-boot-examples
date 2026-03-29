package com.simple.sample.listener;

import com.simple.sample.event.TestEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: kiturone
 * @date: 2019-08-05 23:02
 * @description: 实现事件的监听
 */
@Component
public class TestListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof TestEvent) {
            TestEvent testEvent = (TestEvent) event;
            testEvent.print();
        }

    }
}
