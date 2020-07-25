package com.springboot.websocket.controller;

import com.springboot.websocket.domian.Greeting;
import com.springboot.websocket.domian.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

/**
 * @author: lingjun.jlj
 * @date: 2020/7/18 10:14
 * @description:
 */
@Controller
public class StompController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting send(HelloMessage message) throws InterruptedException {
        // simulated delay
        Thread.sleep(1000);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
