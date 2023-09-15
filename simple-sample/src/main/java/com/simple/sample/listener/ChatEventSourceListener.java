package com.simple.sample.listener;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

/**
 * @author lingjun.jlj
 * @date 2023/9/15 19:41
 */
@Slf4j
public class ChatEventSourceListener extends EventSourceListener {

    private final SseEmitter emitter;

    public ChatEventSourceListener(SseEmitter emitter) {
        this.emitter = emitter;
    }

    @Override
    public void onOpen(@NotNull EventSource eventSource, @NotNull Response response) {
        log.info("建立sse连接...");
    }

    @Override
    public void onEvent(@NotNull EventSource eventSource, String id, final String type, @NotNull String data) {
        log.info("{}: {}", id, data);
        // do something，对接收到的数据进行处理，并向客户端推送
        try {
            emitter.send(SseEmitter.event().data(data));
        } catch (IOException e) {
            log.error("数据发送异常");
        }
    }

    @Override
    public void onClosed(@NotNull EventSource eventSource) {
        log.info("sse连接关闭...");
        emitter.complete();
    }

    @Override
    public void onFailure(@NotNull EventSource eventSource, Throwable t, Response response) {
        log.error("出现异常，返回结果：{}", response, t);
    }
}
