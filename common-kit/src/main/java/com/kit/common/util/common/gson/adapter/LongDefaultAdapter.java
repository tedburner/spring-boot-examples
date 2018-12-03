package com.kit.common.util.common.gson.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * @author Lucifer
 * @data 2018/5/3
 * @Description:
 */
public class LongDefaultAdapter implements JsonSerializer<Long>, JsonDeserializer<Long> {
    public LongDefaultAdapter() {
    }

    public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json.getAsString().equals("") || json.getAsString().equals("null")) {
                return null;
            }
        } catch (Exception var6) {
            ;
        }

        try {
            return json.getAsLong();
        } catch (NumberFormatException var5) {
            throw new JsonSyntaxException(var5);
        }
    }

    public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}
