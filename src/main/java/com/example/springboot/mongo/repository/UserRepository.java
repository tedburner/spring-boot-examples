package com.example.springboot.mongo.repository;

import com.example.springboot.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: lingjun.jlj
 * @Date: 2018/7/11 11:24
 * @Description:
 */
public interface UserRepository extends MongoRepository<User,Long> {
}
