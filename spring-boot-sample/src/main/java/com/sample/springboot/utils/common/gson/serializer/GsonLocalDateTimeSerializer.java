package com.sample.springboot.utils.common.gson.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Lucifer
 * @data 2018/5/3
 * @Description:
 */

public class GsonLocalDateTimeSerializer implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    public GsonLocalDateTimeSerializer() {
    }

    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Long timeStamp = jsonElement.getAsJsonPrimitive().getAsLong();
        Instant instant = Instant.ofEpochMilli(timeStamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        Long timeStamp = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return new JsonPrimitive(timeStamp);
    }
}
