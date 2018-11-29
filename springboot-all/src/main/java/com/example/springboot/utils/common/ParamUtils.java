package com.example.springboot.utils.common;

/**
 * @author Lucifer
 * @date 2018-01-03
 **/
public class ParamUtils {

    public ParamUtils() {
    }

    public static Boolean isParamValid(Long paramId) {
        return paramId != null && paramId.longValue() > 0L;
    }
}
