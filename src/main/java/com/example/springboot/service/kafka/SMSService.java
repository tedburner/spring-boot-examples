package com.example.springboot.service.kafka;

/**
 * @author: Lucifer
 * @date: 2018/11/12 19:17
 * @description:
 */
public interface SMSService {

    /**
     * kafka发送短信
     */
    void sendSMS(String message);
}
