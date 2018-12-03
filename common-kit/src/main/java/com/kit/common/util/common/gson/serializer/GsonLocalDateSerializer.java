package com.kit.common.util.common.gson.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Lucifer
 * @data 2018/5/3
 * @Description:
 */
public class GsonLocalDateSerializer implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    public GsonLocalDateSerializer() {
    }

    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateTimeStr = localDate.format(format);
        return new JsonPrimitive(dateTimeStr);
    }
}
