package com.sample.security.service.impl;

import com.sample.security.entity.User;
import com.sample.security.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    // 模拟数据库存储
    private Map<String, User> userDatabase = new HashMap<>();

    public UserServiceImpl() {
        // 初始化一些测试用户
        GrantedAuthority userRole = new SimpleGrantedAuthority("ROLE_USER");
        GrantedAuthority adminRole = new SimpleGrantedAuthority("ROLE_ADMIN");

        User user1 = new User("user", "{noop}password", Arrays.asList(userRole));
        user1.setEmail("user@example.com");

        User user2 = new User("admin", "{noop}admin", Arrays.asList(userRole, adminRole));
        user2.setEmail("admin@example.com");

        userDatabase.put("user", user1);
        userDatabase.put("admin", user2);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userDatabase.get(username);
    }

    @Override
    public User registerUser(String username, String password, String email) {
        if (userDatabase.containsKey(username)) {
            throw new RuntimeException("User already exists: " + username);
        }

        GrantedAuthority userRole = new SimpleGrantedAuthority("ROLE_USER");
        User newUser = new User(username, "{noop}" + password, Arrays.asList(userRole));
        newUser.setEmail(email);
        userDatabase.put(username, newUser);

        return newUser;
    }
}