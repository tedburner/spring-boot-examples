package com.simple.sample;

import com.simple.sample.service.common.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: lingjun.jlj
 * @date: 2021/2/19 22:16
 * @description:
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public  void batchInsert(){
        userService.batchSave(1000000);
    }
}
