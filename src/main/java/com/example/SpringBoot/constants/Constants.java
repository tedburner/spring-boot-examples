package com.example.SpringBoot.constants;

/**
 * @author lingjun.jlj
 * @data 2018/5/4
 * @Description: 常量
 */
public interface Constants {
    /**
     * 登录有效期
     */
    final static int LOGIN_VALID = 60 * 60 * 24;

    /**
     * 登录态cookic名称
     */
    final static String LOGIN_COOKIC_NAME = "SB-Token";

    /**** Redis key 常量定义  ****/
    final static String REDIS_NAMESPACE_FIELD = "redis_sb_token";
}
