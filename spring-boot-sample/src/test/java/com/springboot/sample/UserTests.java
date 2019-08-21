package com.springboot.sample;

import com.springboot.sample.service.common.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: Lucifer
 * @date: 2018-12-01 14:30
 * @description:
 */
public class UserTests extends SpringBootSampleTests{

    @Autowired
    private UserService userService;


    @Test
    public void findUserTest(){
        System.out.println(userService.findUser());
    }

    @Test
    public void findByIdTest(){
        System.out.println(userService.findById(1L));
    }

    @Test
    public void updateTest(){
       userService.update();
    }

    @Test
    public void findUserByNameTest(){
        userService.findUserByName("测试1");
    }

    @Test
    public void findUserLikeNameTest(){
        userService.findUserLikeName("测试131");
    }
}
