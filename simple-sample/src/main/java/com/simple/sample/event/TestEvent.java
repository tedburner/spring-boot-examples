package com.simple.sample.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author: Arthas
 * @date: 2019-08-05 22:55
 * @description:
 */
public class TestEvent extends ApplicationEvent {

    public String msg;

    public TestEvent(Object source) {
        super(source);
    }

    public TestEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public void print() {
        System.out.println("事件消息：" + msg);
    }
}
