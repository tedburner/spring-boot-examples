package com.sample.security.service;

/**
 * @author: kiturone
 * @date: 2026/04/03 17:27
 * @description: 用户服务接口，继承UserDetailsService
 */

import com.sample.security.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    User registerUser(String username, String password, String email);
}