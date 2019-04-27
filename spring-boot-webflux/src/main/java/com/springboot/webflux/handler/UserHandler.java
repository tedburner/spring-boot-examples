package com.springboot.webflux.handler;

import com.springboot.webflux.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author: Arthas
 * @date: 2019-04-27 19:50
 * @description: 查询用户信息
 */
@Component
public class UserHandler {
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
}
