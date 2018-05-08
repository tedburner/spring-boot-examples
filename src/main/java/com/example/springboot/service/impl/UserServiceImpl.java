package com.example.springboot.service.impl;

import com.example.springboot.model.DO.UserDO;
import com.example.springboot.persist.UserMapper;
import com.example.springboot.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lingjun.jlj
 * @data 2018/4/2
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserDO> findUser() {
        return userMapper.selectUser();
    }
}
