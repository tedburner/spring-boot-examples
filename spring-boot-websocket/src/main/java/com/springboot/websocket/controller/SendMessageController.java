package com.springboot.websocket.controller;

import com.springboot.websocket.param.Person;
import com.springboot.websocket.server.WebSocketEndpoint;
import com.springboot.websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lingjun.jlj
 * @date: 2019/7/17 15:01
 * @version：1.0.0
 * @description:
 */
@RestController
@RequestMapping(value = "/send")
public class SendMessageController {

    @Autowired
    private WebSocketEndpoint webSocketEndpoint;
    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping(value = "/msg/{sid}")
    public String sendMsg(@PathVariable("sid") String sid) {
        Person person = new Person();
        person.setId(sid);
        person.setName("Jack");
        person.setAge("18");
        webSocketEndpoint.sendMessage(sid, person);
        return "发送成功";
    }

    @GetMapping(value = "/ws01/{sid}")
    public String sendMsg01(@PathVariable("sid") String sid) {

        webSocketServer.sendMessage(sid, "用户" + sid + ", 您的账号异地登录了");
        return "发送成功";
    }
}
