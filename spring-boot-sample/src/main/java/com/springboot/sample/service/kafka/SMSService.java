package com.springboot.sample.service.kafka;

import com.springboot.sample.domain.DTO.message.SMSMessageDTO;

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
