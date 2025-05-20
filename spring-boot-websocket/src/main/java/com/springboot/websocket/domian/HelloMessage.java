package com.springboot.websocket.domian;

/**
 * @author: kiturone
 * @date: 2020/7/18 10:24
 * @description:
 */
public class HelloMessage {

    private String name;

    public HelloMessage() {
    }

    public HelloMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
