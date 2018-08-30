package com.example.springboot.service.mongo.impl;

import com.example.springboot.model.DTO.PageDTO;
import com.example.springboot.model.DO.mongo.User;
import com.example.springboot.repository.mongo.UserRepository;
import com.example.springboot.service.mongo.UserMongoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/11 11:41
 * @Description:
 */
@Service
public class UserMongoDBServiceImpl implements UserMongoDBService {

    @Autowired
    private UserRepository userRepository;

    //@Autowired(required = true)
    //private UserPagingAndSortingRepository userPagingAndSortingRepository;
    //private TestPagingAndSortingRepository testPagingAndSortingRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

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

    public User findUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public List<User> findUserByPage(PageDTO pageDTO) {
        //Sort sort = new Sort(Sort.Direction.DESC, "time");

        Pageable pageable = new PageRequest(pageDTO.getStart(), pageDTO.getSize());
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.getContent();
    }
}
