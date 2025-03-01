package com.simple.sample.manager.okhttp;

import com.simple.sample.listener.ChatEventSourceListener;
import com.simple.sample.listener.ChatSimpleEventSourceListener;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.sse.RealEventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * ok http 流式接口
 *
 * @author lingjun.jlj
 * @date 2023/9/15 19:27
 */
@Component
public class OkHttpStreamClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpStreamClient.class);

    private static final OkHttpClient HTTP_CLIENT;

    static {
        HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    /**
     * ok http 实现 sse 监听
     *
     * @param emitter SseEmitter
     * @param url     请求地址
     */
    public void stream(SseEmitter emitter, String url) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        ChatSimpleEventSourceListener listener = new ChatSimpleEventSourceListener(emitter);
        RealEventSource realEventSource = new RealEventSource(request, listener);
        realEventSource.connect(HTTP_CLIENT);
    }

    public void streamAsync(SseEmitter emitter, String url) {
        CompletableFuture<List<String>> future = this.buildChatFuture(url, emitter)
                .thenApply(data -> {
                    LOGGER.info("流式答案数据：{}", data);
                    return data;
                }).exceptionally(ex -> {
                    LOGGER.error("异步处理数据异常", ex);
                    return new ArrayList<>();
                });
        try {
            List<String> answerList = future.get();
            LOGGER.info("获取异步流式结果：{}", answerList);
        } catch (Exception e) {
            LOGGER.error("获取异步答案异常", e);
        }
    }

    /**
     * 通过 CompletableFuture 阻塞处理 sse 并获取流式答案
     *
     * @param url     请求地址
     * @param emitter SseEmitter
     * @return CompletableFuture
     */
    public CompletableFuture<List<String>> buildChatFuture(String url, SseEmitter emitter) {
        CompletableFuture<List<String>> future = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        ChatEventSourceListener listener = new ChatEventSourceListener(emitter, future);
        RealEventSource realEventSource = new RealEventSource(request, listener);
        realEventSource.connect(HTTP_CLIENT);
        return future;
    }
}
