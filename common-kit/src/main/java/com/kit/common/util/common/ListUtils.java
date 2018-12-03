package com.kit.common.util.common;

import java.util.List;

/**
 * Created by jlj on 2017/12/2.
 * List 类型判断方法类
 */
public class ListUtils {

    /**
     * 判断List是否为空
     *
     * @param list
     */
    public static boolean isEmpty(List list) {

        return list == null || list.isEmpty();
    }

    /**
     * 判断List不为空
     *
     * @param list
     */
    public static boolean isNotEmpty(List list) {

        return !isEmpty(list);
    }
}
