package com.ai.knowledge.vector.application.service.impl;

import com.ai.knowledge.vector.application.service.VectorApplicationService;
import com.ai.knowledge.vector.domain.vector.repository.impl.VectorEsStoreRepository;
import com.ai.knowledge.vector.domain.vector.service.EmbeddingTextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lingjun.jlj
 * @date 2025/5/11 21:13
 */
@Service
public class VectorApplicationServiceImpl implements VectorApplicationService {

    private static Logger LOGGER = LoggerFactory.getLogger(VectorApplicationServiceImpl.class);

    @Autowired
    private EmbeddingTextService embeddingTextService;
    @Autowired
    private VectorEsStoreRepository vectorEsStoreRepository;

    @Override
    public void store(String text) {
        // 文本进行向量化
        float[] embedding = embeddingTextService.embedding(text);
        LOGGER.info("文本向量化成功：{}", embedding.length);

        // 构建向量存储对象
        vectorEsStoreRepository.store(text, embedding);
    }

    @Override
    public void autoStore(String text) {
        // 调用 spring ai 框架自行进行存储
        vectorEsStoreRepository.store(text);
    }
}
