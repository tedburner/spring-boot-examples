package com.ai.knowledge.vector.domain.vector.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author kiturone
 * @date 2025/5/5 18:23
 */
@SpringBootTest
public class EmbeddingTextServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddingTextServiceTests.class);

    @Autowired
    private EmbeddingTextService embeddingTextService;

    @Test
    public void testEmbedding() {
        float[] embedding = embeddingTextService.embedding("Hello, world!");
        LOGGER.info("向量化结果：{}", embedding.length);
        Assertions.assertEquals(embedding.length, 768);
    }
}
