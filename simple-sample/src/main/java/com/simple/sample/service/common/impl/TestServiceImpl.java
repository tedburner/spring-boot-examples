package com.simple.sample.service.common.impl;

import com.simple.sample.service.common.AsyncMethodService;
import com.simple.sample.service.common.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * @author: lingjun.jlj
 * @date: 2019/11/27 09:38
 * @description:
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    private final AsyncMethodService asyncMethodService;

    @Autowired
    public TestServiceImpl(AsyncMethodService asyncMethodService) {
        this.asyncMethodService = asyncMethodService;
    }


    @Override
    public void sayHello() {
        asyncMethodService.sayHelloAsync();
        log.info("主线程打印信息...");
    }

    @Override
    public void sayWaitHello() {
        CountDownLatch latch = new CountDownLatch(1);
        asyncMethodService.sayHelloAsync(latch);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("主线程打印信息...");
    }
}
