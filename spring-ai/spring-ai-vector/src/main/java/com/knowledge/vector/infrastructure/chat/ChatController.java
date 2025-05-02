package com.knowledge.vector.infrastructure.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 聊天测试模型
 *
 * @author lingjun.jlj
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
     * 测试大模型
     *
     * @param query 问句
     * @return 结果
     */
    @GetMapping(value = "stream-query")
    public String chat(@RequestParam("query") String query) {
        LOGGER.info("问句：{}", query);
        OllamaOptions options = OllamaOptions.builder()
                // 指定使用哪个大模型，这里使用的是deepseek-r1:8b模型
                .model("deepseek-r1:8b")
                .temperature(0.5D)
                .build();
        final ChatResponse response = ollamaChatModel.call(new Prompt(query, options));
        return response.getResult().getOutput().getText();
    }
}
