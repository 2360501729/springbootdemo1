package com.lcl.pname.controllerconfig.security.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author lcl
 * 创建见简化并复用 redis 的使用工具
 * 声明为组件
 */
@Component
public class RedisCacheUtils {


    /**
     * 导入 redis 自定义模板
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置键值存活时间
     */
    public void setExpire(String hashKey, Long surviveTime,TimeUnit timeUnit) {
        stringRedisTemplate.expire(hashKey, surviveTime, timeUnit);
    }

    /**
     * 添加一对 hash 键值,存储 hash 集合
     *
     * @param hashKey  hash 集合名称
     * @param valueKey 集合中 : 值 的 key
     * @param value    集合中 : 值 value
     * @param surviveTime 存活时长,默认为秒数,默认也永久存储
     */
    public void putValue(String hashKey, String valueKey, Object value,Long... surviveTime) {
        stringRedisTemplate.opsForHash().put(hashKey,valueKey,value);
        if (surviveTime.length > 0) {
            setExpire(hashKey,surviveTime[0],TimeUnit.SECONDS);
        }
    }


    /**
     * 获取 hash 普通类型值
     *
     * @param hashKey  哈希列表 key
     * @param valueKey 值的 key
     */
    public String hGet(String hashKey, String valueKey) {
        return (String) stringRedisTemplate.opsForHash().get(hashKey, valueKey);
    }

    /**
     * 从 hash 中获取指定类型的对象
     * @param hashKey 哈希列表 key
     * @param valueKey 值的 key
     * @param aClass 指定返回类型
     * @return 拿到指定结果
     * @param <T> 指定类型
     */
    public <T> T hGetObj(String hashKey, String valueKey,Class<T> aClass) {
        return (T) stringRedisTemplate.opsForHash().get(hashKey, valueKey);
    }
}
