package com.kit.common.util.mybatis;

import java.util.Collection;

/**
 * @author: kiturone
 * @date: 2019/4/26 09:30
 * @description: mybatis 判断参数方法
 */
public class MyBatisUtils {

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            if (((String) obj).trim().length() == 0) {
                return true;
            }
        } else if (obj instanceof Collection) {
            if (((Collection) obj).isEmpty()) {
                return true;
            }
        } else if (obj.getClass().isArray()) {
            if (((Object[]) obj).length == 0) {
                return true;
            }
        } else if (obj instanceof Long) {
            if (((Long) obj) == null) {
                return true;
            }
        } else if (obj instanceof Short) {
            if (((Short) obj) == null) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
