package com.rowan.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @description: redis相关操作
 * @author: rowan
 * @date: 2020-12-29 15:27:52
 **/
@Repository
public class RedisDao {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取Sting类型值
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * 设置String类型值
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        boolean result = false;
        try {
            ValueOperations valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean setForSeconds(String key, Object value, long expireTime) {
        boolean result = false;
        if (set(key, value) && expire(key, expireTime, TimeUnit.SECONDS)) {
            result = true;
        }
        return result;
    }

    public boolean setForMinutes(String key, Object value, long expireTime) {
        boolean result = false;
        if (set(key, value) && expire(key, expireTime, TimeUnit.MINUTES)) {
            result = true;
        }
        return result;
    }

    public boolean setForHour(String key, Object value, long expireTime) {
        boolean result = false;
        if (set(key, value) && expire(key, expireTime, TimeUnit.HOURS)) {
            result = true;
        }
        return result;
    }

    public boolean setForDay(String key, Object value, long expireTime) {
        boolean result = false;
        if (set(key, value) && expire(key, expireTime, TimeUnit.DAYS)) {
            result = true;
        }
        return result;
    }

    /**
     * 设置失效时间
     *
     * @param key        值
     * @param expireTime 失效时间
     * @param timeUnit   失效类型
     * @return
     */
    public boolean expire(String key, long expireTime, TimeUnit timeUnit) {
        boolean result = false;
        if (timeUnit != null) {
            try {
                redisTemplate.expire(key, expireTime, timeUnit);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 移除失效时间
     *
     * @param key
     */
    public void persist(String key) {
        redisTemplate.persist(key);
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 返回符合表达式的key集合
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 删除指定key的值
     *
     * @param key
     */
    public void delete(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 删除符合表达式的值
     *
     * @param pattern
     */
    public void deletePattern(String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

}
