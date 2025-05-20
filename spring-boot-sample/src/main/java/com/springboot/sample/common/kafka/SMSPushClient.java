package com.springboot.sample.common.kafka;

import com.springboot.sample.common.enums.kafka.KafkaMessageStatusEnum;
import com.springboot.sample.domain.DTO.message.SmsMessageDTO;
import com.springboot.sample.service.kafka.KafkaMessageService;
import com.kit.common.util.common.gson.FormatUtils;
import com.springboot.sample.util.sms.SMSUtils;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author: kiturone
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

    //@KafkaListener(topics = "${kafka.SMS-Topic}")
    public void SMSPushClient(ConsumerRecord<?, ?> record) {
        log.info("start consumer sms message");
        //判断是否NULL
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            log.info("start consumer topic SMS-Topic");
            SmsMessageDTO messageDTO = FormatUtils.str2obj((String) kafkaMessage.get(), new TypeToken<SmsMessageDTO>() {
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
