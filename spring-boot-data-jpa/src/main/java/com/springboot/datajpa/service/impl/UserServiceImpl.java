package com.springboot.datajpa.service.impl;

import com.google.common.collect.Lists;
import com.springboot.datajpa.domain.User;
import com.springboot.datajpa.reposity.UserRepository;
import com.springboot.datajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: lingjun.jlj
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
        Page<User> userList;
        List<User> resultList = Lists.newArrayList();
        int page = 0;
        do {
            userList = userRepository.findAll(new PageRequest(page, PAGE_SIZE));
            page++;
        } while (userList.hasNext());
        return resultList;
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return null;
        }
        return userOptional.get();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<User> findUserStream() {
        try (Stream<User> stream = userRepository.streamAll()) {
            return stream.collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUserName(Long id, String name) {
        userRepository.update(id, name);
    }
}
