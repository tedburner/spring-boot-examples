package com.ai.knowledge.vector.application.service;

import org.springframework.ai.document.Document;

import java.util.List;

/**
 * @author lingjun.jlj
 * @date 2025/5/11 21:13
 */
public interface VectorApplicationService {

    /**
     * 单条文本进行向量存储接口
     *
     * @param text 文本
     */
    void store(String text);

    /**
     * 单条文本进行向量存储接口(自动存储)
     *
     * @param text 文本
     */
    void autoStore(String text);

    /**
     * 向量检索
     *
     * @param text      问句文本
     * @param topK      检索top k
     * @param threshold 阈值
     * @return 检索答案
     */
    List<Document> retrieval(String text, Integer topK, double threshold);

}
