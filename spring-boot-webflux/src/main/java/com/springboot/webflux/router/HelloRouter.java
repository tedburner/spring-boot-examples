package com.springboot.webflux.router;

import com.springboot.webflux.handler.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author: kiturone
 * @date: 2019-01-02 17:37
 * @description: 路由
 */
@Configuration
public class HelloRouter {

    @Bean
    public RouterFunction<ServerResponse> route(HelloHandler helloHandler) {

        return RouterFunctions
                .route(RequestPredicates.GET("/hello")
                        .and(RequestPredicates
                                .accept(MediaType.TEXT_PLAIN)), helloHandler::hello);
    }
}
