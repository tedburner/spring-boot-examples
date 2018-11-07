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

/**
 * @author Lucifer
 * @data 2018/5/3
 * @Description:
 */
public class DoubleDefaultAdapter implements JsonSerializer<Double>, JsonDeserializer<Double> {
    public DoubleDefaultAdapter() {
    }

    public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json.getAsString().equals("") || json.getAsString().equals("null")) {
                return null;
            }
        } catch (Exception var6) {
            ;
        }

        try {
            return json.getAsDouble();
        } catch (NumberFormatException var5) {
            throw new JsonSyntaxException(var5);
        }
    }

    public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}
