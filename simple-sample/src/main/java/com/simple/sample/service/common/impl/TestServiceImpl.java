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
        System.out.println("测试Aop 获取各种属性的方法");
    }
}
