package com.ai.knowledge.vector.domain.vector.entity;

import lombok.Data;

/**
 * 文本向量化结果
 *
 * @author kiturone
 * @date 2025/5/18 11:08
 */
@Data
public class VectorStoreResultDTO {

    /**
     * 成功条数
     */
    private Integer success;

    /**
     * 失败条数
     */
    private Integer fail;

    public VectorStoreResultDTO() {
        this.success = 0;
        this.fail = 0;
    }

    public void increaseSuccess() {
        this.success++;
    }

    public void increaseFail() {
        this.fail++;
    }

    public Integer getSuccess() {
        return success;
    }
}
