package com.ai.chat.interfaces.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * 聊天测试模型
 *
 * @author kiturone
 * @date 2025/5/2 18:06
 */
@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

    private final OllamaChatModel ollamaChatModel;

    public ChatController(OllamaChatModel ollamaChatModel) {
        this.ollamaChatModel = ollamaChatModel;
    }

    /**
     * 流式对话接口
     *
     * @param query 问句
     * @return 结果
     */
    @GetMapping(value = "stream-query", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamQuery(@RequestParam("query") String query) {
        LOGGER.info("问句：{}", query);
        OllamaOptions options = OllamaOptions.builder()
                // 指定使用哪个大模型，这里使用的是deepseek-r1:8b模型
                .model("deepseek-r1:8b")
                .temperature(0.5D)
                .build();
        final Flux<ChatResponse> response = ollamaChatModel.stream(new Prompt(query, options));
        return response.map(chatObj -> chatObj.getResult().getOutput().getText());
    }

    /**
     * 非流式对话接口
     *
     * @param query 问句
     * @return 结果
     */
    @GetMapping(value = "query")
    public Mono<String> chat(@RequestParam("query") String query) {
        LOGGER.info("问句：{}", query);
        OllamaOptions options = OllamaOptions.builder()
                // 指定使用哪个大模型，这里使用的是deepseek-r1:8b模型
                .model("deepseek-r1:8b")
                .temperature(0.5D)
                .build();
        return Mono.fromCallable(() -> {
            final ChatResponse response = ollamaChatModel.call(new Prompt(query, options));
            return response != null && response.getResult() != null && response.getResult().getOutput() != null
                    ? response.getResult().getOutput().getText()
                    : "";
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
