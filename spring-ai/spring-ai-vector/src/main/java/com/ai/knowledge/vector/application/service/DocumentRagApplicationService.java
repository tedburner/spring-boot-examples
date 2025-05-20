package com.ai.knowledge.vector.application.service;

import com.ai.knowledge.vector.interfaces.vo.vector.DocumentRagResultVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文档 RAG 服务
 *
 * @author kiturone
 * @date 2025/5/20 19:33
 */
public interface DocumentRagApplicationService {

    /**
     * 文档解析
     *
     * @param file 文件
     * @return 文档 RAG 结果
     */
    DocumentRagResultVO parse(MultipartFile file);
}
