package com.simple.sample.service.common.impl;

import com.simple.sample.aspect.annotation.AroundCase;
import com.simple.sample.domain.dto.UserDTO;
import com.simple.sample.service.common.AsyncMethodService;
import com.simple.sample.service.common.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${number}")
    private String number;

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

    @Override
//    @PreAuthorize(value = "userDTO.getCardNo()")
    @AroundCase(name = "蒋先森", value = "张三", parm = "userDTO.getCardNo()")
    public void aspectCase(UserDTO userDTO) {
        log.info("测试Aop 获取各种属性的方法");
    }

    @Override
    public void testSynchronizedChar(String str) {
        log.info("{}的：{}", str, str.getBytes());
        //通过将字符串放入字符串常量池，来实现锁住同一个字符串，但是如果数据量大的情况下，可能会出现OOM
        synchronized (str.intern()) {
            log.info("{} 获取了锁", str);
            try {
                log.info("{} is doing something ...", str);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("{} 释放了锁", str);
    }
}
