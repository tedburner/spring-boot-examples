package com.example.springboot;

import com.example.springboot.mongo.entity.UserEntity;
import com.example.springboot.mongo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
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

    @Test
    public void test(){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("username");
        userEntity.setPassword("1234");
        userRepository.insert(userEntity);
    }

}
