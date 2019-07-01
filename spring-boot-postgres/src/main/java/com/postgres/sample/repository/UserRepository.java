package com.postgres.sample.repository;

import com.postgres.sample.domain.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: lingjun.jlj
 * @date: 2019/7/1 16:56
 * @version：1.0
 * @description:
 */
public interface UserRepository extends JpaRepository<UserDO, Long> {
}
