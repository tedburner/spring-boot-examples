package com.springboot.sample.common.constants;

/**
 * @author kiturone
 * @data 2018/5/4
 * @Description: 常量池
 */
public interface Constants {
    /*** 登录有效期***/
    final static int LOGIN_VALID = 60 * 60 * 24;

    /*** 登录态cookic名称***/
    final static String LOGIN_COOKIC_NAME = "SB-Token";

    /**** Redis key 常量定义  ****/
    final static String REDIS_NAMESPACE_FIELD = "redis_sb_token";

    /***图片后缀格式***/
    final static String QINIU_ASSISTANT_EHR_MINIPAC = "mini";
    final static String QINIU_ASSISTANT_EHR_WEBP_PAC = "webp";
    final static String QINIU_ASSISTANT_EHR_JPG_PAC = "jpg";

    final static long IMAGE_UPLOAD_SIZE_LIMIT = 10 * 1024 * 1024 * 8;
}
