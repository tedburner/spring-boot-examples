package com.springboot.webflux.controller;

import com.springboot.webflux.domain.User;
import com.springboot.webflux.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: lingjun.jlj
 * @date: 2019-04-27 20:53
 * @description:
 */
@RestController
@RequestMapping(value = "/user/")
public class UserController {

    @Autowired
    private UserHandler userHandler;

    @GetMapping(value = "/{id}")
    public Mono<User> findCityById(@PathVariable("id") Long id) {
        return userHandler.findCityById(id);
    }

    @GetMapping()
    public Flux<User> findAllCity() {
        return userHandler.findAllUser();
    }


    @PostMapping(value = "save")
    public Mono<Long> saveCity(@RequestBody User user) {
        return userHandler.save(user);
    }

}
