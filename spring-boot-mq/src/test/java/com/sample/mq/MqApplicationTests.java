package com.sample.mq;

import com.sample.mq.constant.MqConstants;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
public class MqApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void priorityQueueTest() {

        for (int i = 0; i < 500; i++) {
            Thread thread = new Thread(() -> {
                Integer priority = ThreadLocalRandom.current().nextInt(10);
                String message = "这是一条优先级为" + priority + "的消息";
                rabbitTemplate.convertAndSend(MqConstants.EXCHANGE_PRIORITY, MqConstants.QUEUE_PRIORITY, message, properties -> {
                    properties.getMessageProperties().setPriority(priority);
                    return properties;
                });
            });
            thread.start();
        }
    }

    @Test
    public void test01() {
        String msg = "{\"bigEndVideoUrl\":\"http://vodqnuhbilj.vod.126.net/vodqnuhbilj/16941814487-51876468525016-1583144190930-0.mp4\",\"bigEndVoiceUrl\":\"http://vodqnuhbilj.vod.126.net/vodqnuhbilj/16941814487-51876468525016-1583144190930-0.aac\",\"callbackUrl\":\"https://szghcpfq.chedai0.com:8080/tp/system/callback/video\",\"channelId\":\"51876468525016\",\"faceviewNumber\":\"158314291000102\",\"machineVideo\":\"\",\"signType\":1,\"smallEndVideoUrl\":\"http://vodqnuhbilj.vod.126.net/vodqnuhbilj/7261981102-51876468525016-1583144190395-0.mp4\",\"smallEndVoiceUrl\":\"http://vodqnuhbilj.vod.126.net/vodqnuhbilj/7261981102-51876468525016-1583144190395-0.aac\",\"videoType\":0,\"watermark\":\"https://szghcpfq.chedai0.com/group1/M00/00/DE/L2FS2l5c3PyATyY0AAAah_pnC2Q195.png\"}";
        rabbitTemplate.convertAndSend("", MqConstants.VIDEO_SYNTHESIS, msg);
    }

}
