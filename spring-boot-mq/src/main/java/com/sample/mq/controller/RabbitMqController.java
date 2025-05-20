package com.sample.mq.controller;

import com.sample.mq.constant.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author: kiturone
 * @date: 2019-07-19 23:18
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/mq")
public class RabbitMqController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @GetMapping(value = "/sendMsg")
    public String sendMsg() {
        String message = "你好，今天是" + LocalDate.now() + "。 今天是晴天，宝宝，我想你了，真的好想你！";
        log.info("向RabbitMQ 发送一条消息，内容：{}", message);
        rabbitTemplate.convertAndSend(MqConstants.EXCHANGE_TEST, MqConstants.QUEUE_TEST, message);
        return "发送成功！";
    }

    @GetMapping(value = "/sendMsg/expire/{timeout}")
    public String sendMsgExpire(@PathVariable("timeout") String timeout) throws Exception {
        String message = "你好，今天是" + sdf.format(new Date());
        MessagePostProcessor processor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(timeout);
                return message;
            }
        };
        rabbitTemplate.convertAndSend(MqConstants.EXCHANGE_MSG_EXPIRE, MqConstants.QUEUE_MSG_EXPIRE, message, processor);
        log.info("向延迟队列【{}】发送了一条消息", MqConstants.QUEUE_MSG_EXPIRE);
        return "发送成功！";
    }

    @GetMapping(value = "/delay")
    public String sendDelayMsg() {
        String message = "你好，今天是" + LocalDate.now();
        rabbitTemplate.convertAndSend(MqConstants.EXCHANGE_QUEUE_EXPIRE_DELAY, MqConstants.QUEUE_QUEUE_EXPIRE_DELAY, message);
        log.info("向延迟队列【{}】发送了一条消息", MqConstants.QUEUE_QUEUE_EXPIRE_DELAY);
        return "发送成功！";
    }

    @GetMapping(value = "/priority/{priority}")
    public String sendDelayMsg(@PathVariable("priority") Integer priority) {
        String message = "这是一条优先级为" + priority + "的消息";
        rabbitTemplate.convertAndSend(MqConstants.EXCHANGE_PRIORITY, MqConstants.QUEUE_PRIORITY, message, properties -> {
            properties.getMessageProperties().setPriority(priority);
            return properties;
        });
        log.info("向优先级队列【{}】发送了一条消息", MqConstants.QUEUE_PRIORITY);
        return "发送成功！";
    }
}
