package com.ai.knowledge.vector.interfaces.controller;

import com.ai.common.http.WebResult;
import com.ai.knowledge.vector.application.service.DocumentRagApplicationService;
import com.ai.knowledge.vector.interfaces.vo.vector.DocumentRagResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文档 RAG
 *
 * @author kiturone
 * @date 2025/5/20 19:23
 */
@RestController
@RequestMapping("/document/rag")
public class DocumentRagController {

    private final DocumentRagApplicationService documentRagApplicationService;

    public DocumentRagController(DocumentRagApplicationService documentRagApplicationService) {
        this.documentRagApplicationService = documentRagApplicationService;
    }

    /**
     * 上传文档并解析文档
     *
     * @param file 上传的文档
     * @return
     */
    @PostMapping("/v1/parse")
    public WebResult uploadParse(@RequestParam("file") MultipartFile file) {
        final DocumentRagResultVO data = documentRagApplicationService.parse(file);
        return WebResult.buildSuccess(data);
    }
}
