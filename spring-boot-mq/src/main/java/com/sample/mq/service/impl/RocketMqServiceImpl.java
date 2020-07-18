package com.sample.mq.service.impl;

import com.sample.mq.constant.MqConstants;
import com.sample.mq.service.RocketMqService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: lingjun.jlj
 * @date: 2020/7/17 23:32
 * @description:
 */
@Slf4j
@Service
public class RocketMqServiceImpl implements RocketMqService {

    private final TransactionMQProducer producer = new TransactionMQProducer(MqConstants.ROCKETMQ_TRANSACTION_PRODUCER_GROUP);

    ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("client-transaction-msg-check-thread");
            return thread;
        }
    });

    @Override
    public void sendTransactionMsg() {
        try {
            Message message = new Message(MqConstants.ROCKETMQ_TRANSACTION_MSG_TOPIC,
                    "TagA", "KEY", ("Hello Rocket mq").getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.sendMessageInTransaction(message, null);
            log.info("send msg result: {}", sendResult);
        } catch (UnsupportedEncodingException | MQClientException e) {
            e.printStackTrace();
        }
    }
}
