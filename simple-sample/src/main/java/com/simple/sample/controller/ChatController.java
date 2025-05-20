package com.simple.sample.controller;

import com.simple.sample.manager.okhttp.OkHttpStreamClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.util.concurrent.CompletableFuture;

/**
 * @author: kiturone
 * @date: 2023-09-15 23:01
 * @description:
 */
@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

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
        Flux.range(0, 10)
                .map(data -> "Server Message # " + data)
                .doOnComplete(emitter::complete)
                .subscribe(data -> {
                    try {
                        emitter.send(SseEmitter.event().data(data));
                    } catch (Exception e) {
                        LOGGER.error("发送数据异常", e);
                    }
                });
        return emitter;
    }

    /**
     * 模拟流式结果输出
     */
    @GetMapping(value = "/v1chat", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter chatSimple() {
        SseEmitter emitter = new SseEmitter(60000L);
        CompletableFuture.runAsync(() -> {
            okHttpStreamClient.stream(emitter, "http://localhost:8888/chat/stream_query");
        });
        emitter.onCompletion(()->{
            LOGGER.info("sse 结束");
        });
        return emitter;
    }


    /**
     * 模拟流式结果输出
     */
    @GetMapping(value = "chat", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter chat() {
        SseEmitter emitter = new SseEmitter(60000L);
        CompletableFuture.runAsync(() -> {
            okHttpStreamClient.streamAsync(emitter, "http://localhost:8888/chat/stream_query");
        });
        emitter.onCompletion(()->{
            LOGGER.info("sse 结束");
        });
        return emitter;
    }
}
