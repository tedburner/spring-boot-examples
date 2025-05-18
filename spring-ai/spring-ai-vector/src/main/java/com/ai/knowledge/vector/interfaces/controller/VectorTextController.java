package com.ai.knowledge.vector.interfaces.controller;

import com.ai.common.http.WebResult;
import com.ai.knowledge.vector.application.service.VectorApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public WebResult embedding(@RequestParam("text") String text) {
        return WebResult.buildSuccess(vectorApplicationService.embedding(text));
    }

    /**
     * 文本向量化
     *
     * @param text 文本
     * @return 向量表示
     */
    @GetMapping("/v1/store")
    public WebResult store(@RequestParam("text") String text) {
        return WebResult.buildSuccess(vectorApplicationService.store(text));
    }

    /**
     * 使用框架自动存储
     *
     * @param text 文本
     * @return 向量表示
     */
    @GetMapping("/v1/auto/store")
    public WebResult autoStore(@RequestParam("text") String text) {
        return WebResult.buildSuccess(vectorApplicationService.autoStore(text));
    }
}
