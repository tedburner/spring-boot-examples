package com.springboot.websocket.domian;

/**
 * @author: lingjun.jlj
 * @date: 2020/7/18 10:24
 * @description:
 */
public class Greeting {

    private String content;

    public Greeting() {
    }

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
