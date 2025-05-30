package com.simple.sample.listener;

import com.simple.sample.manager.okhttp.OkHttpStreamClient;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

/**
 * @author kiturone
 * @date 2023/9/15 19:41
 */
public class ChatSimpleEventSourceListener extends EventSourceListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpStreamClient.class);

    private final SseEmitter emitter;

    public ChatSimpleEventSourceListener(SseEmitter emitter) {
        this.emitter = emitter;
    }

    @Override
    public void onOpen(@NotNull EventSource eventSource, @NotNull Response response) {
        LOGGER.info("建立sse连接...");
    }

    @Override
    public void onEvent(@NotNull EventSource eventSource, String id, final String type, @NotNull String data) {
        LOGGER.info("{}: {}", id, data);
        // do something，对接收到的数据进行处理，并向客户端推送
        try {
            emitter.send(SseEmitter.event().data(data));
        } catch (IOException e) {
            LOGGER.error("数据发送异常", e);
        }
    }

    @Override
    public void onClosed(@NotNull EventSource eventSource) {
        LOGGER.info("sse连接关闭...");
        emitter.complete();
    }

    @Override
    public void onFailure(@NotNull EventSource eventSource, Throwable t, Response response) {
        LOGGER.error("出现异常，返回结果：{}", response, t);
    }
}
