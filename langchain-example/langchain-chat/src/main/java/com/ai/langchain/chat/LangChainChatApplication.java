package com.ai.langchain.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基于langchain 实现的大模型ai对话系统
 *
 * @author lingjun.jlj
 * @date 2025/5/7 20:00
 */
@SpringBootApplication
public class LangChainChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(LangChainChatApplication.class, args);
    }

}
