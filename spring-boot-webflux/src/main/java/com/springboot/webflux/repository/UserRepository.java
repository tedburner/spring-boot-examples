package com.springboot.webflux.repository;

import com.springboot.webflux.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: lingjun.jlj
 * @date: 2019-05-16 22:49
 * @description:
 */

public interface UserRepository extends JpaRepository<User, Long> {
}
