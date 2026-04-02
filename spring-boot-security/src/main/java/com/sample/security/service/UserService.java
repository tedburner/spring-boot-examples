package com.sample.security.service;

import com.sample.security.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    User registerUser(String username, String password, String email);
}