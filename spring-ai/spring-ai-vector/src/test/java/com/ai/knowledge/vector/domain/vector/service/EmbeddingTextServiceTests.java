package com.ai.knowledge.vector.domain.vector.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lingjun.jlj
 * @date 2025/5/5 18:23
 */
@SpringBootTest
public class EmbeddingTextServiceTests {

    @Autowired
    private EmbeddingTextService embeddingTextService;

    @Test
    public void testEmbedding() {
        float[] embedding = embeddingTextService.embedding("Hello, world!");
        System.out.println(embedding);
        Assertions.assertEquals(embedding.length, 768);
    }
}
