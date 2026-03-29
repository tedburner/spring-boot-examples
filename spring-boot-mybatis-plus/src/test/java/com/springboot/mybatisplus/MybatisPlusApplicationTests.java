package com.springboot.mybatisplus;

import com.springboot.mybatisplus.domain.UserInfo;
import com.springboot.mybatisplus.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void save() {
        userInfoService.save("张三","111111", "222222222",30);
    }

    @Test
    public void findAll() {
        List<UserInfo> list = userInfoService.findAll();
        log.info("所有用户信息：{}", list);
    }

    @Test
    public void findName() {
        List<UserInfo> list = userInfoService.findByName("张三");
        log.info("用户信息：{}", list);
    }

}
