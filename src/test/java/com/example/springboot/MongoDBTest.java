package com.example.springboot;

import com.example.springboot.mongo.entity.User;
import com.example.springboot.mongo.repository.UserRepository;
import com.example.springboot.service.mongo.UserMongoDBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lingjun.jlj
 * @data 2018/5/9
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoDBTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMongoDBService userService;

    @Test
    public void save() {
        User userEntity = new User();
        userEntity.setId("1");
        userEntity.setName("编写");
        userEntity.setPassword("呵呵");
        userRepository.insert(userEntity);
    }

    @Test
    public void find() {
        System.out.println("查询内容：" + userService.findUserAll());
    }

}
