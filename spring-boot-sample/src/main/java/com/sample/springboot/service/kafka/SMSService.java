package com.sample.springboot.service.kafka;

import com.sample.springboot.domain.DTO.message.SMSMessageDTO;

/**
 * @author: Lucifer
 * @date: 2018/11/12 19:17
 * @description:
 */
public interface SMSService {

    /**
     * kafka发送短信
     */
    void sendSMS(SMSMessageDTO message);
}
