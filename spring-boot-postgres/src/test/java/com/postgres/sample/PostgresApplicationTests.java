package com.postgres.sample;

import com.postgres.sample.domain.UserDO;
import com.postgres.sample.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostgresApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void saveTest() {
        UserDO user = new UserDO();
        user.setUsername("张三");
        user.setPassword("123456");
        user.setIdCard("330987123456689087");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userService.save(user);
    }

    @Test
    public void findAllTest() {
        System.out.println(userService.findUserList());
    }

}
