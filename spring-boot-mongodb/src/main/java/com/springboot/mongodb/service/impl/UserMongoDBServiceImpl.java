package com.springboot.mongodb.service.impl;

import com.springboot.mongodb.domain.PageDTO;
import com.springboot.mongodb.domain.User;
import com.springboot.mongodb.repository.mongo.UserRepository;
import com.springboot.mongodb.service.UserMongoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author: kiturone
 * @Date: 2018/7/11 11:41
 * @Description:
 */
@Service
public class UserMongoDBServiceImpl implements UserMongoDBService {

    private final UserRepository userRepository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserMongoDBServiceImpl(UserRepository userRepository, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));

        Update update = new Update();
        update.set("name", user.getName());
        update.set("password", user.getPassword());
        mongoTemplate.updateFirst(query, update, User.class);
    }

    @Override
    public List<User> findUserAll() {
        List<User> user = userRepository.findAll();
        return user;
    }

    @Override
    public List<User> findUserByName(String name) {
        List<User> userList = userRepository.findByName(name);
        return userList;
    }

    @Override
    public List<User> findUserByNameLike(String name) {
        List<User> userList = userRepository.findByNameLike(name);
        return userList;
    }

    @Override
    public User findUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public List<User> findUserByPage(PageDTO pageDTO) {
        Sort sort = Sort.by(Sort.Direction.DESC, "time");
        Pageable pageable = PageRequest.of(pageDTO.getStart(), pageDTO.getSize(), sort);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.getContent();
    }
}
