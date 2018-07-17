package com.example.springboot;

import com.example.springboot.model.DTO.PageDTO;
import com.example.springboot.mongo.entity.User;
import com.example.springboot.mongo.repository.UserRepository;
import com.example.springboot.service.mongo.UserMongoDBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.jvm.hotspot.debugger.Page;

/**
 * @author lingjun.jlj
 * @data 2018/5/9
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoDBTest {

    @Autowired
    private UserMongoDBService userService;

    @Test
    public void save() {
        for (int i = 2; i < 1000; i++) {
            User user = new User();
            user.setId(String.valueOf(i));
            user.setName("编写" + i);
            user.setPassword("呵" + i + "呵");
            userService.save(user);
        }
    }

    @Test
    public void find() {
        System.out.println("查询内容：" + userService.findUserAll());
    }

    @Test
    public void findName() {
        System.out.println("查询内容：" + userService.findUserByName("编写"));

        System.out.println("查询内容：" + userService.findUserById("1"));

        System.out.println("查询内容：" + userService.findUserByNameLike("写2"));
        PageDTO pageDTO = new PageDTO();
        pageDTO.setSize(10);
        pageDTO.setStart(0);
        System.out.println("查询内容：" + userService.findUserByPage(pageDTO));
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(String.valueOf(1));
        user.setName("update" );
        user.setPassword("呵呵,update");
       userService.update(user);
    }

}
