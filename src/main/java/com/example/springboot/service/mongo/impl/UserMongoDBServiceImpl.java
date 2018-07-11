package com.example.springboot.service.mongo.impl;

import com.example.springboot.mongo.entity.User;
import com.example.springboot.mongo.repository.UserRepository;
import com.example.springboot.service.mongo.UserMongoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/11 11:41
 * @Description:
 */
@Service
public class UserMongoDBServiceImpl implements UserMongoDBService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findUserAll() {
        List<User> user = userRepository.findAll();
        return user;
    }
}
