package com.simple.sample.service.common.impl;

import com.simple.sample.domain.entity.User;
import com.simple.sample.service.common.AsyncMethodService;
import com.simple.sample.service.common.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author: lingjun.jlj
 * @date: 2019/11/27 10:19
 * @description:
 */
@Slf4j
@Service
public class AsyncMethodServiceImpl implements AsyncMethodService {

    private final UserService userService;

    @Autowired
    public AsyncMethodServiceImpl(UserService userService) {
        this.userService = userService;
    }

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
            List<User> userList = userService.findAll();
            log.info("打印查询的用户数据：{}", userList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
        log.info("async say Hello World!!!!");
    }
}
