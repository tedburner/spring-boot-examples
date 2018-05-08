package com.example.springboot.constants;

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

    /***七牛云图片***/
    final static String ACCESS_KEY = "h9IJoaqmp7VN3ttWi7b8W99r_N13ywPgQbgvd2oR";

    final static String SECRET_KEY = "wso7YGanzpsSwAOibk72JRvYlHsXJx4TMgotHnHs";

    final static String QINIU_ASSISTANT_EHR_MINIPAC = "mini";
    final static String QINIU_ASSISTANT_EHR_WEBP_PAC = "webp";// webp样式
    final static String QINIU_ASSISTANT_EHR_JPG_PAC = "jpg";// jpg样式

    final static long IMAGE_UPLOAD_SIZE_LIMIT = 10 * 1024 * 1024 * 8;
}
