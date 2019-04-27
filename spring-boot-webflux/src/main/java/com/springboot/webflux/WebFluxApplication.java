package com.springboot.webflux;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : Arthas
 * @description: 反应式编程事例启动项
 * */
@SpringBootApplication
public class WebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);

//        GreetingWebClient gwc = new GreetingWebClient();
//        System.out.println(gwc.getResult());
    }

}

