package com.postgres.sample.service.impl;

import com.postgres.sample.domain.UserDO;
import com.postgres.sample.repository.UserRepository;
import com.postgres.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lingjun.jlj
 * @date: 2019/7/1 16:57
 * @version：1.0
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(UserDO userDO) {
        userRepository.save(userDO);
    }

    @Override
    public List<UserDO> findUserList() {
        return userRepository.findAll();
    }
}
