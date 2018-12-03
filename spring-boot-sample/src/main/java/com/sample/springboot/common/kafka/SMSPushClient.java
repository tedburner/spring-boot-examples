package com.sample.springboot.common.kafka;

import com.sample.springboot.common.enums.kafka.KafkaMessageStatusEnum;
import com.sample.springboot.domain.DTO.message.SMSMessageDTO;
import com.sample.springboot.service.kafka.KafkaMessageService;
import com.kit.common.util.common.gson.FormatUtils;
import com.sample.springboot.util.sms.SMSUtils;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author: Lucifer
 * @date: 2018/11/12 19:23
 * @description:
 */
@Slf4j
@Component
public class SMSPushClient {

    @Autowired
    private SMSUtils smsUtils;
    @Autowired
    private KafkaMessageService kafkaMessageService;

    @KafkaListener(topics = "${kafka.SMS-Topic}")
    public void SMSPushClient(ConsumerRecord<?, ?> record) {
        log.info("start consumer sms message");
        //判断是否NULL
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            log.info("start consumer topic SMS-Topic");
            SMSMessageDTO messageDTO = FormatUtils.str2obj((String) kafkaMessage.get(), new TypeToken<SMSMessageDTO>() {
            }.getType());
            log.info("message : " + messageDTO);
            try {
                //发送短信
                smsUtils.sendSMS(messageDTO);

                //修改数据库状态
                kafkaMessageService.updateKafkaMessage(KafkaMessageStatusEnum.COMPLETE.getCode(), messageDTO.getKafkaMessageId());

                log.info("kafka consumer success");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("kafka consumer error : " + e);
                //发送失败，修改状态
                kafkaMessageService.updateKafkaMessage(KafkaMessageStatusEnum.FAIL.getCode(), messageDTO.getKafkaMessageId());
            }
        }
    }
}
