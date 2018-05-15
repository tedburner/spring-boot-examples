package com.example.springboot.utils.common.gson;

import com.example.springboot.utils.common.StringUtils;
import com.example.springboot.utils.common.gson.adapter.BigDecimalDefaultAdapter;
import com.example.springboot.utils.common.gson.adapter.DoubleDefaultAdapter;
import com.example.springboot.utils.common.gson.adapter.IntegerDefaultAdapter;
import com.example.springboot.utils.common.gson.adapter.LongDefaultAdapter;
import com.example.springboot.utils.common.gson.adapter.StringAdapter;
import com.example.springboot.utils.common.gson.serializer.GsonDateSerializer;
import com.example.springboot.utils.common.gson.serializer.GsonLocalDateTimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author lingjun.jlj
 * @data 2018/5/3
 * @Description:
 */
public class FormatUtils {

    private static Logger log = LoggerFactory.getLogger(FormatUtils.class);
    private static final Gson gson = (new GsonBuilder()).registerTypeAdapter(Date.class,
            new GsonDateSerializer()).registerTypeAdapter(String.class,
            new StringAdapter()).registerTypeAdapter(LocalDateTime.class,
            new GsonLocalDateTimeSerializer()).registerTypeAdapter(Long.class,
            new LongDefaultAdapter()).registerTypeAdapter(Double.class,
            new DoubleDefaultAdapter()).registerTypeAdapter(Integer.class,
            new IntegerDefaultAdapter()).registerTypeAdapter(BigDecimal.class,
            new BigDecimalDefaultAdapter()).disableHtmlEscaping().create();

    public FormatUtils() {
    }

    public static <T> String obj2str(T obj) throws JsonIOException {
        return gson.toJson(obj);
    }

    public static <T> T str2obj(String str, Type t) throws JsonSyntaxException {
        try {
            return StringUtils.isEmpty(str) ? null : gson.fromJson(str, t);
        } catch (Exception var3) {
            log.error("str2obj error, str=" + str, var3);
            return null;
        }
    }
}
