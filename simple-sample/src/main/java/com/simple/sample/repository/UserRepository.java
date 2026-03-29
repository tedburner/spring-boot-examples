package com.simple.sample.repository;

import com.simple.sample.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author: kiturone
 * @date: 2018-12-26 16:55
 * @description:
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("update User u set u.name = ?2 where u.id = ?1")
    int update(Long id, String name);

}
