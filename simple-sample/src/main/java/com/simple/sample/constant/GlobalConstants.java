package com.simple.sample.constant;

/**
 * @author: kiturone
 * @date: 2021/4/28 16:21
 * @description:
 */
public class GlobalConstants {

    public static final String REDIS_LOCK = "REDIS_LOCK";

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String COMMA_SEPARATOR = ",";
    public static final String DOT_SEPARATOR = ".";
    public static final String SEMICOLON_SEPARATOR = ";";
    public static final String REGISTRY_SEPARATOR = "|";
    public static final String COMMA_SEPARATOR_CHAR = "，";
    public static final String COMMA_POUND_SIGN_CHAR = "#";
    public static final String COMMA_SEMICOLON_CHAR = ":";

    /**定时任务*/
        /**每隔5秒执行一次*/
//    public static final String TASK_DEFAULT_CRON = "0/5 * * * * ?";
                /**每隔5分钟执行一次*/
    public static final String TASK_DEFAULT_CRON = "0 0/5 * * * ?";
}
