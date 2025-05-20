package com.ai.knowledge.vector.interfaces.assembler;

import com.ai.knowledge.vector.domain.vector.entity.VectorStoreResultDTO;
import com.ai.knowledge.vector.interfaces.vo.vector.VectorStoreResultVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 文本向量化结果
 *
 * @author kiturone
 * @date 2025/5/18 11:08
 */
@Mapper
public interface VectorStoreResultAssembler {

    VectorStoreResultAssembler INSTANCE = Mappers.getMapper(VectorStoreResultAssembler.class);

    /**
     * 文本向量化结果
     *
     * @param resultDTO 文本向量化结果
     * @return 文本向量化结果
     */
    @Mappings({
            @Mapping(source = "success", target = "success"),
            @Mapping(source = "fail", target = "fail")
    })
    VectorStoreResultVO toVo(VectorStoreResultDTO resultDTO);
}
