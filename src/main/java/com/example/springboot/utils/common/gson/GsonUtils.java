package com.example.springboot.utils.common.gson;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lingjun.jlj
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

    public static boolean isGoodJson(String json) {
        if (StringUtils.isBlank(json)) {
            return false;
        } else {
            try {
                return (new JsonParser()).parse(json).isJsonObject();
            } catch (JsonParseException var2) {
                return false;
            }
        }
    }
}
