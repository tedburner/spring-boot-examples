package com.springboot.datajpa;

import com.springboot.datajpa.domain.User;
import com.springboot.datajpa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataJpaTests {

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        int sum = 1000;
        for (int k = 0; k<1000;k++) {
            List<User> userList = new ArrayList<>(sum+1);
            for (int i = 0; i < sum; i++) {
                log.info("添加第" + i);
                User user = new User();
                user.setName("第" + i + "号人的名字");
                user.setAge(i);
                user.setPassword(String.valueOf(i + 20181226));
                Long car = 330781199601230992L + i;
                user.setCard(String.valueOf(car));
                Long phone = 13500000000L + i;
                user.setPhone(String.valueOf(phone));
                LocalDateTime now = LocalDateTime.now();
                user.setCreateTime(now);
                user.setUpdateTime(now);
                userList.add(user);
            }
            userService.save(userList);
        }
    }

    @Test
    public void findAll() {
        System.out.println(userService.findUserAll());
    }

    @Test
    public void findStreamAll() {
        System.out.println(userService.findUserStream());
    }

}

