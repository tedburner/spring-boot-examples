package com.kit.common.util.common.gson.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Lucifer
 * @data 2018/5/3
 * @Description:
 */
public class GsonLocalTimeSerializer implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {
    public GsonLocalTimeSerializer() {
    }

    public LocalTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalTime.parse(jsonElement.getAsJsonPrimitive().getAsString());
    }

    public JsonElement serialize(LocalTime localTime, Type type, JsonSerializationContext jsonSerializationContext) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        String dateTimeStr = localTime.format(format);
        return new JsonPrimitive(dateTimeStr);
    }
}
