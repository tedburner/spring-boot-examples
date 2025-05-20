package com.ai.knowledge.vector.infrastructure.util;


/**
 * id 生成工具
 *
 * @author kiturone
 * @date 2025/5/11 10:36
 */
public class IdUtil {


    /**
     * 生成id
     *
     * @return id
     */
    public static String getId() {
        return String.valueOf(System.currentTimeMillis());
    }
}
