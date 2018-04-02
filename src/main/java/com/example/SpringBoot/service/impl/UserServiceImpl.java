package com.example.SpringBoot.service.impl;

import com.example.SpringBoot.dto.DO.UserDO;
import com.example.SpringBoot.persist.UserMapper;
import com.example.SpringBoot.service.UserService;
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
