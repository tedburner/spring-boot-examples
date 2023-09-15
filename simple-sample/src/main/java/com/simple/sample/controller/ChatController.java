package com.simple.sample.controller;

import com.simple.sample.manager.okhttp.OkHttpStreamClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author: lingjun.jlj
 * @date: 2023-09-15 23:01
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    private final OkHttpStreamClient okHttpStreamClient;

    @Autowired
    public ChatController(OkHttpStreamClient okHttpStreamClient) {
        this.okHttpStreamClient = okHttpStreamClient;
    }

    /**
     * 模拟流式结果输出
     */
    @GetMapping(value = "stream_query", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter streamQuery() {
        SseEmitter emitter = new SseEmitter(60000L);
        Flux.interval(Duration.ofSeconds(1))
                .map(data -> "Server Message # " + data)
                .doOnCancel(emitter::complete)
                .subscribe(data -> {
                    try {
                        emitter.send(SseEmitter.event().data(data));
                    } catch (Exception e) {
                        log.error("发送数据异常", e);
                    }
                });
        return emitter;
    }


    /**
     * 模拟流式结果输出
     */
    @GetMapping(value = "chat", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter chat() {
        SseEmitter emitter = new SseEmitter(60000L);
        okHttpStreamClient.stream(emitter, "http://localhost:8888/chat/stream_query");
        return emitter;
    }
}
