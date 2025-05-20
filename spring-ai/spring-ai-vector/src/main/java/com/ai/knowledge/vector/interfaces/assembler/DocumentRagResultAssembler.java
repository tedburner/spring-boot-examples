package com.ai.knowledge.vector.interfaces.assembler;

import com.ai.knowledge.vector.domain.vector.entity.DocumentRagResultDTO;
import com.ai.knowledge.vector.interfaces.vo.vector.DocumentRagResultVO;

/**
 * 知识库文档解析数据转换
 *
 * @author kiturone
 * @date 2025/5/20 19:39
 */
public interface DocumentRagResultAssembler {

    /**
     * 数据转换
     *
     * @param dto dto 数据
     * @return vo 数据
     */
    DocumentRagResultVO toVo(DocumentRagResultDTO dto);
}
