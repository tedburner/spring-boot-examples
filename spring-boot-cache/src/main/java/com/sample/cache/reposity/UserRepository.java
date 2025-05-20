package com.sample.cache.reposity;

import com.sample.cache.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: kiturone
 * @date: 2018-12-26 16:55
 * @description:
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
