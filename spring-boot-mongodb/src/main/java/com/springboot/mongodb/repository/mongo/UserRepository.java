package com.springboot.mongodb.repository.mongo;

import com.springboot.mongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author: Lucifer
 * @Date: 2018/7/11 11:24
 * @Description:
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * 根据name查询
     *
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 根据name查询
     *
     * @param name
     * @return
     */
    List<User> findByNameLike(String name);
}
