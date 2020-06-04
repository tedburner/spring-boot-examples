package com.kit.common.util.common.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kit.common.test.UserDTO;
import com.kit.common.util.common.StringUtils;
import com.kit.common.util.common.gson.adapter.BigDecimalDefaultAdapter;
import com.kit.common.util.common.gson.adapter.DoubleDefaultAdapter;
import com.kit.common.util.common.gson.adapter.IntegerDefaultAdapter;
import com.kit.common.util.common.gson.adapter.LongDefaultAdapter;
import com.kit.common.util.common.gson.adapter.StringAdapter;
import com.kit.common.util.common.gson.serializer.GsonDateSerializer;
import com.kit.common.util.common.gson.serializer.GsonLocalDateSerializer;
import com.kit.common.util.common.gson.serializer.GsonLocalDateTimeSerializer;
import com.kit.common.util.common.gson.serializer.GsonLocalTimeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lingjun.jlj
 * @data 2018/5/3
 * @Description:
 */
public class FormatUtils {
    private static Logger log = LoggerFactory.getLogger(FormatUtils.class);

    /**
     * 重写序列化，解决java8时间以及别的一些冲突问题
     */
    private static final Gson gson = (new GsonBuilder())
            .registerTypeAdapter(Date.class, new GsonDateSerializer())
            .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeSerializer())
            .registerTypeAdapter(LocalDate.class, new GsonLocalDateSerializer())
            .registerTypeAdapter(LocalTime.class, new GsonLocalTimeSerializer())
            .registerTypeAdapter(String.class, new StringAdapter())
            .registerTypeAdapter(Long.class, new LongDefaultAdapter())
            .registerTypeAdapter(Double.class, new DoubleDefaultAdapter())
            .registerTypeAdapter(Integer.class, new IntegerDefaultAdapter())
            .registerTypeAdapter(BigDecimal.class, new BigDecimalDefaultAdapter())
            .disableHtmlEscaping().create();

    public FormatUtils() {
    }

    /**
     * 对象转化成json
     *
     * @param obj 对象
     * @return json
     */
    public static <T> String obj2str(T obj) {
        return gson.toJson(obj);
    }

    /**
     * json 字符串转换成对象
     *
     * @param str  json 字符串
     * @param type 转换类型 Type type = new TypeToken<t>() {}.getType();
     * @return
     */
    public static <T> T str2obj(String str, Type type) {
        try {
            return StringUtils.isEmpty(str) ? null : gson.fromJson(str, type);
        } catch (Exception e) {
            log.error("gson str2obj error, str=" + str, e);
            return null;
        }
    }

    public static void main(String[] args) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("张三");
        userDTO.setDate(new Date());
        userDTO.setLocalDate(LocalDate.now());
        userDTO.setLocalTime(LocalTime.now());
        userDTO.setLocalDateTime(LocalDateTime.now());
        String json = FormatUtils.obj2str(userDTO);
        System.out.println("Object 转换成 json 数据：" + json);

        Type type = new TypeToken<UserDTO>() {
        }.getType();
        UserDTO dto = FormatUtils.str2obj(json, type);
        System.out.println("json 转换成 Object 数据：" + dto);

        List<UserDTO> list = new ArrayList<>();
        list.add(userDTO);
        String listJson = FormatUtils.obj2str(list);
        System.out.println("List 转换成 json 数据：" + listJson);

        Type listType = new TypeToken<List<UserDTO>>() {
        }.getType();
        List<UserDTO> list1 = FormatUtils.str2obj(listJson, listType);
        System.out.println("json 转换成 List 数据：" + list1);
    }
}
