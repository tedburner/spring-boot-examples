package com.springboot.webflux.handler;

import com.springboot.webflux.domain.User;
import com.springboot.webflux.exception.GlobalException;
import com.springboot.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author: lingjun.jlj
 * @date: 2019-04-27 19:50
 * @description: 查询用户信息
 */
@Component
public class UserHandler {

    @Autowired
    private UserRepository userRepository;

    public Mono<ServerResponse> user(ServerRequest request) {
        return ServerResponse.ok().body(findCity(request), String.class);
    }

    private Mono<String> findCity(ServerRequest request) {
        System.out.println("findCity------->" + request.queryParam("user"));
        Optional<String> userParam = request.queryParam("user");
        if (!userParam.isPresent()) {
            throw new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR, "request param city is ERROR");
        }
        return Mono.just("Hello," + userParam.get());
    }

    public Mono<Long> save(User user) {
        //return Mono.create(userMonoSink -> userMonoSink.success(userRepository.save(user)));
        return null;
    }

    public Mono<User> findCityById(Long id) {
        return Mono.justOrEmpty(userRepository.findById(id));
    }

    public Flux<User> findAllUser() {
        return Flux.fromIterable(userRepository.findAll());
    }


//    public Mono<Long> deleteUser(Long id) {
//        return Mono.create(userMonoSink -> userMonoSink.success(userRepository.deleteById(id)));
//    }

}
