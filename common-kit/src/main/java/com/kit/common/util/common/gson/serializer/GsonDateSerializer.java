package com.kit.common.util.common.gson.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author Lucifer
 * @data 2018/5/3
 * @Description:
 */
public class GsonDateSerializer implements JsonSerializer<Date>, JsonDeserializer<Date> {

    public GsonDateSerializer() {
    }

    @Override
    public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
        return new JsonPrimitive(date.getTime());
    }

    @Override
    public Date deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        return new Date(element.getAsJsonPrimitive().getAsLong());
    }
}

