package com.zhita.util;

import java.util.Set;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisClientUtil {

    /**
     * 连接池
     */
    private static JedisPool pool;

    static {
        pool = new JedisPool("127.0.0.1", 6379);
    }

    /**
     * Redis存储简单类型
     * 
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        Jedis jedis = pool.getResource();
        String set = jedis.set(key, value);
        jedis.close();
        return set;
    }
       

    /**
     * 根据key，获取值
     * 
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = pool.getResource();
        String value = jedis.get(key);
        jedis.expire(key,180);        //过期时间设置单位为秒
        jedis.close();
        return value;
    }
    
    /**
     * 根据key，获取值
     * 
     * @param key
     * @return
     */
    public String getSourceClick(String key) {
        Jedis jedis = pool.getResource();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }
    
    /**
     * 获取全部的key
     * 
     * @param key
     * @return
     */
    public Set<String> getkeyAll() {
        Jedis jedis = pool.getResource();
        Set<String> value = jedis.keys("*");
        jedis.close();
        return value;
    }
    
    /**
     * 删除指定的key
     * 
     * @param key
     * @return
     */
    public long delkey(String key) {
        Jedis jedis = pool.getResource();
        Long value = jedis.del(key);
        jedis.close();
        return value;
    }

    /**
     * ProtostuffIOUtil序列化对象并使用Redis存储
     * 
     * @param key
     * @param value
     * @param type
     * @return
     */
    public String setObject(String key, Object value, Class<?> type) {
        Jedis jedis = pool.getResource();
        byte[] byteKey = key.getBytes();
        @SuppressWarnings("unchecked")
        RuntimeSchema<Object> schema = (RuntimeSchema<Object>) RuntimeSchema
                .createFrom(type);
        // 序列化
        byte[] bytes = ProtostuffIOUtil.toByteArray(value, schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        String set = jedis.set(byteKey, bytes);
        jedis.close();
        return set;
    }

    /**
     * ProtostuffIOUtil反序列对象并返回Redis中的对象
     * 
     * @param key
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    public Object getObject(String key, Class<?> type) {
        Jedis jedis = pool.getResource();
        byte[] bytes = jedis.get(key.getBytes());
        RuntimeSchema<Object> schema = (RuntimeSchema<Object>) RuntimeSchema
                .createFrom(type);
        Object result = schema.newMessage();// 创建空对象
        ProtostuffIOUtil.mergeFrom(bytes, result, schema);// 把字节数组的数据存入result对象中
        jedis.close();
        return result;
    }

}
