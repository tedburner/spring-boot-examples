package com.springboot.datajpa.reposity;

import com.springboot.datajpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

/**
 * @author: Arthas
 * @date: 2018-12-26 16:55
 * @description:
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE))
    @Query(value = "select u from User u")
    Stream<User> findAllUser();
}
