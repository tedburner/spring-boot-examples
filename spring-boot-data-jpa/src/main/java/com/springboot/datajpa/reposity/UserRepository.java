package com.springboot.datajpa.reposity;

import com.springboot.datajpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

/**
 * @author: lingjun.jlj
 * @date: 2018-12-26 16:55
 * @description:
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE))
    @Query(value = "select u from User u ")
    Stream<User> streamAll();

    @Modifying
    @Query("update User u set u.name = ?2 where u.id = ?1")
    int update(Long id, String name);

}
