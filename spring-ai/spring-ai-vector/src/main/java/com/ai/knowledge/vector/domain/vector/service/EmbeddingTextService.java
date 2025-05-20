package com.ai.knowledge.vector.domain.vector.service;

/**
 * 向量化文本服务
 * 用于将文本转换为向量表示
 *
 * @author kiturone
 * @date 2025/5/5 17:26
 */
public interface EmbeddingTextService {

    /**
     * 文本向量化
     *
     * @param text 文本
     * @return 向量表示
     */
    float[] embedding(String text);
}
