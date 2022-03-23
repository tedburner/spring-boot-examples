package com.example.fluent.mybatis.service.impl;

import com.example.fluent.mybatis.entity.UserEntity;
import com.example.fluent.mybatis.mapper.UserMapper;
import com.example.fluent.mybatis.service.UserService;
import com.example.fluent.mybatis.wrapper.UserQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

/**
 * @author: lingjun.jlj
 * @date: 2022/3/22 22:23
 * @description:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserEntity findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public UserEntity findByName(String name) {
        Optional<UserEntity> optional = userMapper.findOne(UserEntity.class,
                new UserQuery()
                        .where
                        .name().eq(name)
                        .end());
        return optional.orElse(null);
    }

    @Override
    public void save() {
        UserEntity user = new UserEntity();
        user.setName("宋江");
        user.setAge(20);
        user.setCard("222222");
        user.setPhone("111111111");
        user.setStatus(1);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insert(user);
    }
}
