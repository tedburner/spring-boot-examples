package com.ai.knowledge.vector.domain.vector.service.impl;

import com.ai.knowledge.vector.domain.vector.service.EmbeddingTextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.stereotype.Service;

/**
 * @author kiturone
 * @date 2025/5/5 17:26
 */
@Service
public class EmbeddingTextServiceImpl implements EmbeddingTextService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddingTextServiceImpl.class);

    private final OllamaEmbeddingModel ollamaEmbeddingModel;

    public EmbeddingTextServiceImpl(OllamaEmbeddingModel ollamaEmbeddingModel) {
        this.ollamaEmbeddingModel = ollamaEmbeddingModel;
    }

    @Override
    public float[] embedding(String text) {
       return ollamaEmbeddingModel.embed(new Document(text));

    }
}
