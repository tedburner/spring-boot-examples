package com.ai.knowledge.vector.interfaces.controller;

import com.ai.knowledge.vector.application.service.VectorApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lingjun.jlj
 * @date 2025/5/17 10:34
 */
@RestController
@RequestMapping("/vector/text")
public class VectorTextController {

    private final VectorApplicationService vectorApplicationService;

    public VectorTextController(VectorApplicationService vectorApplicationService) {
        this.vectorApplicationService = vectorApplicationService;
    }

    /**
     * 文本向量化
     *
     * @param text 文本
     * @return 向量表示
     */
    @GetMapping("/v1/embedding")
    public List<Float> embedding(String text) {
        return vectorApplicationService.embedding(text);
    }

    /**
     * 文本向量化
     *
     * @param text 文本
     * @return 向量表示
     */
    @GetMapping("/v1/store")
    public void store(String text) {
        vectorApplicationService.store(text);
    }
}
