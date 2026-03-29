package com.kit.common.util.common.gson;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: kiturone
 * @data 2018/5/3
 * @Description:
 */
public final class GsonUtils {

    protected static final Logger logger = LoggerFactory.getLogger(GsonUtils.class);

    public GsonUtils() {
    }

    public static boolean isBadJson(String json) {
        return !isGoodJson(json);
    }

    /**
     * 判断字符串是否是一个合格的json
     *
     * @param json json字符串
     * @return true是；false 否
     */
    public static boolean isGoodJson(String json) {
        if (StringUtils.isBlank(json)) {
            return false;
        } else {
            try {
                return JsonParser.parseString(json).isJsonObject();
            } catch (JsonParseException var2) {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        String json = "{\n" +
                "  \"id\": 1,\n" +
                "  \"username\": \"张三\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"cardNo\": \"330781199809082330\"\n" +
                "}";

        System.out.println("不是json: " + GsonUtils.isBadJson(json));
        System.out.println("是是json: " + GsonUtils.isGoodJson(json));
    }
}
