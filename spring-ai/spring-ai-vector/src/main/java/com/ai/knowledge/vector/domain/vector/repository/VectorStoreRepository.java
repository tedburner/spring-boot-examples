package com.ai.knowledge.vector.domain.vector.repository;

import com.ai.knowledge.vector.domain.vector.entity.VectorStoreResultDTO;
import org.springframework.ai.document.Document;

import java.util.List;

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
     * @return 存储结果
     */
    VectorStoreResultDTO store(String text);

    /**
     * 单条文本向量化存储
     * <p>
     * 自己执行elasticsearch 进行文本存储
     *
     * @param text      文本
     * @param embedding 向量化内容
     * @return 存储结果
     */
    VectorStoreResultDTO store(String text, float[] embedding);

    /**
     * 向量检索
     *
     * @param text      检索文本
     * @param topK      检索内容
     * @param threshold 相似度阈值
     * @return 向量搜索结果
     */
    List<Document> retrieval(String text, Integer topK, double threshold);
}
