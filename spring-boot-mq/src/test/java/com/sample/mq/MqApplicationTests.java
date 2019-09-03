package com.sample.mq;

import com.sample.mq.constant.MqConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringRunner.class)
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

}
