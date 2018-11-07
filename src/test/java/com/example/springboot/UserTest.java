package com.example.springboot;

import com.example.springboot.model.DO.UserDO;
import com.example.springboot.service.common.UserService;
import com.example.springboot.service.mail.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucifer
 * @data 2018/5/9
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void addUser() {
        for (int i = 0; i < 100; i++) {
            Long phone = 17826852170L;
            UserDO user = UserDO.UserDOBuilder
                    .anUserDO()
                    .withName("test" + i)
                    .withPassword("12344" + i)
                    .withPhone(String.valueOf(phone + 1L))
                    .withAge(i)
                    .build();

            userService.addUser(user);
        }
    }

    @Test
    public void update() {
        List<UserDO> userList = userService.findUser();

        userList.forEach(user -> {
            Long card = 330781199509082330L;
            UserDO userDO = new UserDO();
            userDO.setId(user.getId());
            userDO.setCard(String.valueOf(card + 1L));
            userService.update(userDO);
        });
    }
}
