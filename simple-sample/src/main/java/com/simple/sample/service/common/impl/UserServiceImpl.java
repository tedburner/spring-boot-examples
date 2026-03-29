package com.simple.sample.service.common.impl;

import com.simple.sample.domain.entity.User;
import com.simple.sample.repository.UserRepository;
import com.simple.sample.service.common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: kiturone
 * @date: 2019-08-03 22:58
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new RuntimeException("用户不存在");
    }

    @Override
    public void batchSave(Integer num) {
        Long cardId = 330781199509082000L;
        Long phone = 15888982123L;
        for (int i = 0; i < num; i++) {
            User user = new User();
            user.setName("test" + i);
            user.setPassword("password" + i);
            user.setAge(i);
            user.setCard(String.valueOf(cardId + i));
            user.setPhone(String.valueOf(phone + i));
            userRepository.save(user);
        }
    }
}
