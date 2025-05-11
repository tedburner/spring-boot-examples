package com.ai.knowledge.vector.application.service;

/**
 * @author lingjun.jlj
 * @date 2025/5/11 21:13
 */
public interface VectorApplicationService {

    /**
     * 单条文本进行向量存储接口
     *
     * @param text
     */
    void store(String text);

    /**
     * 单条文本进行向量存储接口(自动存储)
     *
     * @param text
     */
    void autoStore(String text);
}
