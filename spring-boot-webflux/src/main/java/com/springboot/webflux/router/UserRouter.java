package com.springboot.webflux.router;

import com.springboot.webflux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author: kiturone
 * @date: 2019-04-27 20:11
 * @description:
 */
@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> routeUser(UserHandler userHandler) {

        return RouterFunctions
                .route(RequestPredicates.GET("/getUser")
                        .and(RequestPredicates
                                .accept(MediaType.TEXT_PLAIN)), userHandler::user);
    }
}
