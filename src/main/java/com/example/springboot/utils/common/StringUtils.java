package com.example.springboot.utils.common;

/**
 * Created by jlj on 2017/12/2.
 * String类型判断
 * 导入 common-lang3.jar 即可
 */
public class StringUtils {

    /**
     * 判断String不存在，空字符串返回为false
     *
     * @param obj
     */
    public static boolean isEmpty(String obj) {

        return obj == null || obj.isEmpty();
    }

    /**
     * 判断String存在
     *
     * @param obj
     */
    public static boolean isNotEmpty(String obj) {

        return !isEmpty(obj);
    }

    /**
     * 判断cs是否存在,空字符返回true
     *
     * @param cs
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

}
