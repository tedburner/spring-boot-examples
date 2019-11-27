package com.simple.sample.service.common.impl;

import com.simple.sample.service.common.AsyncMethodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * @author: lingjun.jlj
 * @date: 2019/11/27 10:19
 * @description:
 */
@Slf4j
@Service
public class AsyncMethodServiceImpl implements AsyncMethodService {

    @Override
    @Async
    public void sayHelloAsync() {
        log.info("异步操作开始");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("async say Hello World!!!!");
    }

    @Override
    @Async
    public void sayHelloAsync(CountDownLatch latch) {
        log.info("异步操作开始");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
        log.info("async say Hello World!!!!");
    }
}
