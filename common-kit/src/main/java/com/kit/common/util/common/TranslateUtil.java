package com.kit.common.util.common;

import java.math.BigDecimal;

/**
 * @author Lucifer
 * @date 2017-12-15
 **/
public class TranslateUtil {

    /**
     * 将对象转化为String，如果对象为null，则返回""
     *
     * @param obj
     * @return
     */
    public static String getStrOfObj(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static int getIntOfObj(Object obj) {
        if (obj == null || !isNumber(obj)) {
            return 0;
        } else {
            String str = obj.toString();
            return Integer.parseInt(str.split("\\.")[0]);
        }
    }

    public static double getDoubleOfObj(Object obj) {
        if (obj == null || !isNumber(obj)) {
            return 0L;
        } else {
            return Double.parseDouble(obj.toString());
        }
    }

    /**
     * 将对象转化为long，如果转化不了则返回0
     *
     * @param obj
     * @return
     */
    public static long getLongOfObj(Object obj) {
        if (obj == null) {
            return 0L;
        }
        String str = obj.toString();
        if (isNumber(str)) {
            return Long.parseLong(str.split("\\.")[0]);
        } else if (isScientificNumber(str)) {
            BigDecimal bd = new BigDecimal(obj.toString());
            return Long.parseLong(bd.toPlainString());
        } else {
            return 0L;
        }

    }

    /**
     * 判断某字符串是否是纯数字,如“12345”或“100.222”
     *
     * @param string
     * @return boolean
     */
    public static boolean isNumber(Object string) {
        String regex = "-?\\d+\\.?\\d*";
        String str = String.valueOf(string);
        if (str.endsWith(".")) {
            return false;
        }
        if (str.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断某字符串是不是科学计数法的数字,如“1.646E10”
     *
     * @param string
     * @return
     */
    public static boolean isScientificNumber(Object string) {
        //科学计数法正则表达式
        String regex = "^((-?\\d+.?\\d*)[Ee]{1}(-?\\d+))$";
        String str = String.valueOf(string);
        if (str.endsWith(".")) {
            return false;
        }
        if (str.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }
}
