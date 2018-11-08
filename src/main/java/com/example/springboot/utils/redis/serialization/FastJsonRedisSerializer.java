package com.example.springboot.utils.redis.serialization;


/**
 * @author Lucifer
 * @data 2018/4/27
 * @Description:
 */
public class FastJsonRedisSerializer<T> //implements RedisSerializer<T>
{
//    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
//    private Class<T> clazz;
//
//    public FastJsonRedisSerializer(Class<T> clazz) {
//        super();
//        this.clazz = clazz;
//    }
//
//    @Override
//    public byte[] serialize(T t) throws SerializationException {
//        if (null == t) {
//            return new byte[0];
//        }
//        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
//    }
//
//    @Override
//    public T deserialize(byte[] bytes) throws SerializationException {
//        if (null == bytes || bytes.length <= 0) {
//            return null;
//        }
//        String str = new String(bytes, DEFAULT_CHARSET);
//        return (T) JSON.parseObject(str, clazz);
//    }
}
