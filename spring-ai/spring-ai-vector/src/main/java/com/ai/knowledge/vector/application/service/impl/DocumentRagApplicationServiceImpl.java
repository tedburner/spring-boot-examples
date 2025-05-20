package com.ai.knowledge.vector.application.service.impl;

import com.ai.knowledge.vector.application.service.DocumentRagApplicationService;
import com.ai.knowledge.vector.domain.vector.service.DocumentRagService;
import com.ai.knowledge.vector.domain.vector.service.EmbeddingTextService;
import com.ai.knowledge.vector.interfaces.vo.vector.DocumentRagResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author kiturone
 * @date 2025/5/20 19:33
 */
@Service
public class DocumentRagApplicationServiceImpl implements DocumentRagApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentRagApplicationServiceImpl.class);

    private final DocumentRagService documentRagService;
    private final EmbeddingTextService embeddingTextService;

    public DocumentRagApplicationServiceImpl(DocumentRagService documentRagService,
                                             EmbeddingTextService embeddingTextService) {
        this.documentRagService = documentRagService;
        this.embeddingTextService = embeddingTextService;
    }

    @Override
    public DocumentRagResultVO parse(MultipartFile file) {
        // 1.拆分解析文档
        // 2.生成文本向量
        // 3.存储文本向量
        // 4.返回结果
        return null;
    }
}
