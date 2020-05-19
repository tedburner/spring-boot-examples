package com.kit.common.util.common.gson.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author: lingjun.jlj
 * @data 2018/5/3
 * @Description:
 */

public class StringAdapter extends TypeAdapter<String> {
    public StringAdapter() {
    }

    @Override
    public String read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return "";
        } else {
            return reader.nextString();
        }
    }

    @Override
    public void write(JsonWriter writer, String value) throws IOException {
        if (value == null) {
            writer.value("");
        } else {
            writer.value(value);
        }
    }
}
