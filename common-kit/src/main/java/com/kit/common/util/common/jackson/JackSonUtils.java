package com.kit.common.util.common.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.kit.common.test.UserDTO;
import com.kit.common.util.common.StringUtils;
import com.kit.common.util.common.gson.FormatUtils;
import com.kit.common.util.common.jackson.serializer.JacksonLongDeserializer;
import com.kit.common.util.common.jackson.serializer.JacksonLongSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: lingjun.jlj
 * @date: 2020/5/19 15:05
 * @description: jackson使用工具
 */
public class JackSonUtils {

    private static Logger log = LoggerFactory.getLogger(FormatUtils.class);

    /**自定义序列化程序*/
    private static final SimpleModule module = (new SimpleModule())
            .addSerializer(Long.class, new JacksonLongSerializer())
            .addDeserializer(Long.class, new JacksonLongDeserializer());

    /**
     * 解决java8时间冲突问题
     */
    private static final ObjectMapper objectMapper = (new ObjectMapper())
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .registerModule(module);

    /**
     * 对象转化成json
     *
     * @param obj 对象
     * @return json
     */
    public static <T> String obj2str(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("jackson obj2str error, obj={}, e={}", obj, e.getMessage());
            return null;
        }
    }

    /**
     * json 字符串转换成对象
     *
     * @param str   json 字符串
     * @param clazz 转换类型
     * @return
     */
    public static <T> T str2obj(String str, Class<T> clazz) {
        try {
            return StringUtils.isEmpty(str) ? null : objectMapper.readValue(str, clazz);
        } catch (JsonProcessingException e) {
            log.error("jackson str2obj error, str={}, e={}", str, e.getMessage());
            return null;
        }
    }

    /**
     * json 字符串转换成对象
     *
     * @param str  json 字符串
     * @param type 转换类型
     * @return
     */
    public static <T> T str2obj(String str, JavaType type) {
        try {
            return StringUtils.isEmpty(str) ? null : objectMapper.readValue(str, type);
        } catch (JsonProcessingException e) {
            log.error("jackson str2obj error, str={}, e={}", str, e.getMessage());
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
        String json = JackSonUtils.obj2str(userDTO);
        System.out.println("Object 转换成 json 数据：" + json);

        UserDTO dto = JackSonUtils.str2obj(json, UserDTO.class);
        System.out.println("json 转换成 Object 数据：" + dto);

        List<UserDTO> list = new ArrayList<>();
        list.add(userDTO);
        String listJson = JackSonUtils.obj2str(list);
        System.out.println("List 转换成 json 数据：" + listJson);

        JavaType type = objectMapper.getTypeFactory()
                .constructType(List.class, UserDTO.class);
        List<UserDTO> list1 = JackSonUtils.str2obj(listJson, type);
        System.out.println("json 转换成 List 数据：" + list1);
    }
}
