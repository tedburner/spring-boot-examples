package com.springboot.datajpa.service.impl;

import com.google.common.collect.Lists;
import com.springboot.datajpa.domain.User;
import com.springboot.datajpa.reposity.UserRepository;
import com.springboot.datajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Arthas
 * @date: 2018-12-26 16:55
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    final int PAGE_SIZE = 1000;


    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void save(List<User> users) {
        userRepository.saveAll(users);
    }


    @Override
    public List<User> findUserAll() {
        Page<User> userList ;
        List<User> resultList = Lists.newArrayList();
        int page = 0;
        do {
            userList= userRepository.findAll(new PageRequest(page, PAGE_SIZE));
            resultList.addAll(userList.getContent());
        }while (userList.hasNext());
        return resultList;
    }

    @Override
    public List<User> findUserStream() {
        return userRepository.findAllUser()
                .collect(Collectors.toList());
    }
}
