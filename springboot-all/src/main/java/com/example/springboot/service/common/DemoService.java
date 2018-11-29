package com.example.springboot.service.common;

/**
 * @author Lucifer
 * @data 2018/4/28
 * @Description:
 */
public interface DemoService {

    void show();

    void showCity();

    int getNumer();

    /**
     * 发送短信
     */
    void sendMessage();

    /**
     * kafka 测试
     *
     * @param message 消息内容
     */
    void sendTestMessage(String message);
}
