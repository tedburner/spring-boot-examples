package com.example.docker.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: kiturone
 * @date: 2018-12-03 16:55
 * @description:
 */
@RestController
@RequestMapping(value = "/hello/")
public class HelloDockerController {

    @Value("${docker.param}")
    private String dockerParam;

    ThreadLocal<String> threadLocal = new ThreadLocal();

    @GetMapping(value = "say")
    public String sayHello() {

        try {
            System.out.println("ThreadLocal写入数据");
            threadLocal.set("测试ThreadLocal");
            Thread.sleep(20000);
            System.out.println(threadLocal.get());
        } catch (Exception e) {

        }
        return threadLocal.get();
    }


    @GetMapping(value = "gc")
    public void gc() {
        System.out.println("JVM GC");
        System.gc();
    }

    @GetMapping(value = "/docker")
    public String getDocker() {
        return dockerParam;
    }

}
