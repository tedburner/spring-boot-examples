package com.ai.knowledge.vector.domain.vector.repository;

/**
 * @author lingjun.jlj
 * @date 2025/5/11 10:36
 */
public interface VectorStoreRepository {

    /**
     * 单条文本向量化存储
     * <p>
     * spring ai 框架自动进行了文本向量化
     *
     * @param text 文本
     */
    void store(String text);

    /**
     * 单条文本向量化存储
     * <p>
     * 自己执行elasticsearch 进行文本存储
     *
     * @param text      文本
     * @param embedding 向量化内容
     */
    void store(String text, float[] embedding);
}
