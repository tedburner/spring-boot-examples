package com.simple.sample.manager.okhttp;

import com.simple.sample.listener.ChatEventSourceListener;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.sse.RealEventSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.TimeUnit;

/**
 * ok http 流式接口
 *
 * @author lingjun.jlj
 * @date 2023/9/15 19:27
 */
@Component
public class OkHttpStreamClient {

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
        ChatEventSourceListener listener = new ChatEventSourceListener(emitter);
        RealEventSource realEventSource = new RealEventSource(request, listener);
        realEventSource.connect(HTTP_CLIENT);
    }
}
