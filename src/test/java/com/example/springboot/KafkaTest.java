package com.example.springboot;


import com.aliyuncs.exceptions.ClientException;
import com.example.springboot.domain.DTO.message.SMSMessageDTO;
import com.example.springboot.service.kafka.KafkaMessageService;
import com.example.springboot.utils.common.gson.FormatUtils;
import com.example.springboot.utils.sms.SMSUtils;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lingjun.jlj
 * @data 2018/5/15
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTest {

    @Autowired
    private KafkaMessageService kafkaMessageService;
    @Autowired
    private SMSUtils smsUtils;

    @Test
    public void sms() throws ClientException {
        String parma = "{\"phone\":\"17826852173\",\"templateCode\":\"SMS_150743936\",\"templateParam\":\"{\\\"code\\\":\\\"1234\\\"}\"}";
        SMSMessageDTO messageDTO = FormatUtils.str2obj(parma, new TypeToken<SMSMessageDTO>() {
        }.getType());
        smsUtils.sendSms(messageDTO);
    }

}
