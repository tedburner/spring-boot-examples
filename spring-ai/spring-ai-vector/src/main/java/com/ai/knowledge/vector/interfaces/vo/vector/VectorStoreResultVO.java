package com.ai.knowledge.vector.interfaces.vo.vector;

import lombok.Data;

/**
 * 文本向量化结果
 *
 * @author lingjun.jlj
 * @date 2025/5/18 11:08
 */
@Data
public class VectorStoreResultVO {

    /**
     * 成功条数
     */
    private Integer success;

    /**
     * 失败条数
     */
    private Integer fail;
}
