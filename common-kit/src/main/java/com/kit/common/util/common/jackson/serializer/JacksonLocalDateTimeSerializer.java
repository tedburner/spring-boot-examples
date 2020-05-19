package com.kit.common.util.common.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author: lingjun.jlj
 * @date: 2020/5/19 15:52
 * @description:
 */
public class JacksonLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        long timeStamp = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        gen.writeString(Long.toString(timeStamp));
    }
}
