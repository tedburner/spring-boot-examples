package com.ai.knowledge.vector.application.service.impl;

import com.ai.knowledge.vector.application.service.VectorApplicationService;
import com.ai.knowledge.vector.domain.vector.entity.VectorStoreResultDTO;
import com.ai.knowledge.vector.domain.vector.repository.VectorStoreRepository;
import com.ai.knowledge.vector.domain.vector.service.EmbeddingTextService;
import com.ai.knowledge.vector.interfaces.assembler.VectorStoreResultAssembler;
import com.ai.knowledge.vector.interfaces.vo.vector.VectorStoreResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author lingjun.jlj
 * @date 2025/5/11 21:13
 */
@Service
public class VectorApplicationServiceImpl implements VectorApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VectorApplicationServiceImpl.class);

    private final EmbeddingTextService embeddingTextService;
    private final VectorStoreRepository vectorStoreRepository;

    public VectorApplicationServiceImpl(EmbeddingTextService embeddingTextService,
                                        VectorStoreRepository vectorStoreRepository) {
        this.embeddingTextService = embeddingTextService;
        this.vectorStoreRepository = vectorStoreRepository;
    }

    @Override
    public List<Float> embedding(String text) {
        final float[] embedding = embeddingTextService.embedding(text);
        return IntStream.range(0, embedding.length)
                .mapToObj(i -> embedding[i])
                .toList();

    }

    @Override
    public VectorStoreResultVO store(String text) {
        // 文本进行向量化
        float[] embedding = embeddingTextService.embedding(text);
        LOGGER.info("文本向量化成功：{}", embedding.length);

        // 构建向量存储对象
        final VectorStoreResultDTO result = vectorStoreRepository.store(text, embedding);
        return VectorStoreResultAssembler.INSTANCE.toVo(result);
    }

    @Override
    public VectorStoreResultVO autoStore(String text) {
        // 调用 spring ai 框架自行进行存储
        final VectorStoreResultDTO result = vectorStoreRepository.store(text);
        return VectorStoreResultAssembler.INSTANCE.toVo(result);
    }

    @Override
    public List<Document> retrieval(String text, Integer topK, double threshold) {
        return vectorStoreRepository.retrieval(text, topK, threshold);
    }
}
