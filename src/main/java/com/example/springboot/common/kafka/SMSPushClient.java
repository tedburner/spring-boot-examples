package com.example.springboot.common.kafka;

import com.example.springboot.common.enums.kafka.KafkaMessageStatusEnum;
import com.example.springboot.domain.DTO.message.SMSMessageDTO;
import com.example.springboot.service.kafka.KafkaMessageService;
import com.example.springboot.utils.common.gson.FormatUtils;
import com.example.springboot.utils.sms.SMSUtils;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author: Lucifer
 * @date: 2018/11/12 19:23
 * @description:
 */
@Slf4j
public class SMSPushClient {

    @Autowired
    private SMSUtils smsUtils;
    @Autowired
    private KafkaMessageService kafkaMessageService;

    @KafkaListener(topics = "SMS-Topic")
    public void SMSPushClient(String msgBody) {
        log.info("start consumer sms");
        SMSMessageDTO messageDTO = FormatUtils.str2obj(msgBody, new TypeToken<SMSMessageDTO>() {
        }.getType());
        try {
            //发送短信
            smsUtils.sendSms(messageDTO);

            //修改数据库状态
            kafkaMessageService.updateKafkaMessage(KafkaMessageStatusEnum.COMPLETE.getCode(), messageDTO.getKafkaMessageId());

            log.info("kafka consumer success");
        } catch (Exception e) {
            log.error("kafka consumer error");
            //发送失败，修改状态
            kafkaMessageService.updateKafkaMessage(KafkaMessageStatusEnum.FAIL.getCode(), messageDTO.getKafkaMessageId());
        }
    }
}
