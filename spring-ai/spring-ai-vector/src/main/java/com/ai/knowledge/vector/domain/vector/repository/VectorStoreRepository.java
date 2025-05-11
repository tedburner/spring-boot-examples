package com.ai.knowledge.vector.domain.vector.repository;

/**
 * @author lingjun.jlj
 * @date 2025/5/11 10:36
 */
public interface VectorStoreRepository {

    /**
     * 单条文本向量化存储
     *
     * @param text      文本
     * @param embedding 向量化内容
     */
    void store(String text, float[] embedding);
}
