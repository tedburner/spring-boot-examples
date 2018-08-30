package com.example.springboot.utils.common.gson.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * @author lingjun.jlj
 * @data 2018/5/3
 * @Description:
 */
public class BigDecimalDefaultAdapter implements JsonSerializer<BigDecimal>, JsonDeserializer<BigDecimal> {
    public BigDecimalDefaultAdapter() {
    }

    public BigDecimal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json.getAsString().equals("") || json.getAsString().equals("null")) {
                return null;
            }
        } catch (Exception var6) {
            ;
        }

        try {
            return json.getAsBigDecimal();
        } catch (NumberFormatException var5) {
            throw new JsonSyntaxException(var5);
        }
    }

    public JsonElement serialize(BigDecimal src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}
