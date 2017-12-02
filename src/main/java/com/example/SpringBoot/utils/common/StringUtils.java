package com.example.SpringBoot.utils.common;

/**
 * Created by jlj on 2017/12/2.
 * String类型判断
 */
public class StringUtils {

    /**
     * 判断String不存在
     * @param obj
     * */
    public boolean isEmpty(String obj){

        return obj == null || obj.isEmpty();
    }

    /**
     * 判断String存在
     * @param obj
     * */
    public boolean isNotEmpty(String obj){

        return !isEmpty(obj);
    }
}
