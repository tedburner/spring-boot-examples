package com.kit.common.util.common.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.kit.common.test.UserDTO;
import com.kit.common.util.common.StringUtils;
import com.kit.common.util.common.gson.FormatUtils;
import com.kit.common.util.common.jackson.serializer.JacksonLocalDateTimeDeserializer;
import com.kit.common.util.common.jackson.serializer.JacksonLocalDateTimeSerializer;
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
public class JacksonUtils {

    private static Logger log = LoggerFactory.getLogger(FormatUtils.class);

    /**
     * 自定义序列化程序
     */
    private static final SimpleModule module = (new SimpleModule())
            .addSerializer(Long.class, new JacksonLongSerializer())
            .addDeserializer(Long.class, new JacksonLongDeserializer())
            .addSerializer(LocalDateTime.class, new JacksonLocalDateTimeSerializer())
            .addDeserializer(LocalDateTime.class, new JacksonLocalDateTimeDeserializer());

    /**
     * 解决java8时间冲突问题
     */
    private static final ObjectMapper objectMapper = (new ObjectMapper())
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            //序列化结果中不包含null值
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            //允许：默认false_不解析含有结束语控制字符
            .enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature())
            //主要是这行配置，意思是在遇到未知字段时是否失败，默认为true，也就是遇到未知字段时会报错
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
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
     * @param str           json 字符串
     * @param typeReference 转换类型 TypeReference<T> type = new TypeReference<T>() {};
     * @return
     */
    public static <T> T str2obj(String str, TypeReference<T> typeReference) {
        try {
            return StringUtils.isEmpty(str) ? null : objectMapper.readValue(str, typeReference);
        } catch (JsonProcessingException e) {
            log.error("jackson str2obj error, str={}, e={}", str, e.getMessage());
            return null;
        }
    }


    /**
     * 反序列化json 为 集合
     *
     * @param src   json
     * @param clazz 对象
     * @param <T>   对象类型
     * @return 反序列化后的对象
     */
    public static <T> List<T> toArray(String src, Class<T> clazz) {
        if (src == null || clazz == null) {
            return null;
        }
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return objectMapper.readValue(src, javaType);
        } catch (JsonProcessingException e) {
            log.error("parse json[{}] to array error", src, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 从json串中获取某个节点的值：子json
     *
     * @param src json
     * @return 子json
     */
    public static String childJson(String src, String node) {
        try {
            JsonNode jsonNode = objectMapper.readTree(src);
            return jsonNode.get(node).toString();
        } catch (Exception e) {
            log.error("parse json[{}] node[{}], error", src, node, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 从json串中获取某个节点的值：json的节点的值
     *
     * @param src json
     * @return string
     */
    public static String childStr(String src, String node) {
        try {
            JsonNode jsonNode = objectMapper.readTree(src);
            return jsonNode.get(node).asText();
        } catch (Exception e) {
            log.error("get json[{}] node[{}], error", src, node, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 从json串中获取某个节点的值：json的节点的值
     *
     * @param src json
     * @return int
     */
    public static int childInt(String src, String node) {
        try {
            JsonNode jsonNode = objectMapper.readTree(src);
            return jsonNode.get(node).asInt();
        } catch (Exception e) {
            log.error("get json[{}] node[{}], error", src, node, e);
            throw new RuntimeException(e);
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
        String json = JacksonUtils.obj2str(userDTO);
        System.out.println("Object 转换成 json 数据：" + json);

        UserDTO dto = JacksonUtils.str2obj(json, UserDTO.class);
        System.out.println("json 转换成 Object 数据：" + dto);

        List<UserDTO> list = new ArrayList<>();
        list.add(userDTO);
        String listJson = JacksonUtils.obj2str(list);
        System.out.println("List 转换成 json 数据：" + listJson);

        TypeReference<List<UserDTO>> type = new TypeReference<List<UserDTO>>() {
        };
        List<UserDTO> list1 = JacksonUtils.str2obj(listJson, type);
        System.out.println("json 转换成 List 数据：" + list1);

        String str = "{\"id\":\"1\",\"name\":\"张三\",\"date\":1589877644094,\"localDateTime\":\"1589877644098\",\"localDate\":[2020,5,19],\"localTime\":[16,40,44,98000000],\"password\":\"123456\"}";
        System.out.println("json 数据中有多余字段的 字符串转换成 Object 数据：" + JacksonUtils.str2obj(str, UserDTO.class));

        // child
        String name = JacksonUtils.childStr(json, "name");
        System.out.println(name);
    }
}
