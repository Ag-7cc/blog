package com.sqb.blog.dal.dao;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by vic
 * Create time : 2017/7/26 下午4:20
 */
@Component
public class RedisDao {

    @Resource(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void init() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
    }

    /**
     * String相关 start
     */
    public boolean set(String key, Object value) {
        redisTemplate.boundValueOps(key).set(value);
        return true;
    }

    public boolean set(String key, Object value, long time) {
        redisTemplate.boundValueOps(key).set(value, time, TimeUnit.SECONDS);
        return true;
    }

    public Object get(String key) {
        return redisTemplate.boundValueOps(key).get();
    }
    /** String相关 end */

    /**
     * Set相关 start
     */
    public boolean sadd(String key, Object value) {
        redisTemplate.boundSetOps(key).add(value);
        return true;
    }

    public boolean sadd(String key, Object value, long time) {
        redisTemplate.boundSetOps(key).add(value);
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
        return true;
    }

    public Set<Object> smembers(String key) {
        return redisTemplate.boundSetOps(key).members();
    }
    /** Set相关 end */

    /**
     * List相关 start
     */
    public long llen(String key) {
        return redisTemplate.boundListOps(key).size();
    }

    public boolean lpush(String key, Object value) {
        redisTemplate.boundListOps(key).leftPush(value);
        return true;
    }

    public boolean lpush(String key, Object value, long time) {
        redisTemplate.boundListOps(key).leftPush(value);
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
        return true;
    }

    public boolean rpush(String key, Object value) {
        redisTemplate.boundListOps(key).rightPush(value);
        return true;
    }

    public boolean rpush(String key, Object value, long time) {
        redisTemplate.boundListOps(key).rightPush(value);
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
        return true;
    }

    public List<Object> lrange(String key, long start, long stop) {
        return redisTemplate.boundListOps(key).range(start, stop);
    }

    public boolean ltrim(String key, long start, long end) {
        redisTemplate.boundListOps(key).trim(start, end);
        return true;
    }
    /** List相关 end */

    /**
     * Common start
     */
    public boolean delete(String key) {
        redisTemplate.delete(key);
        return true;
    }

    public boolean delete(Set<String> keys){
        redisTemplate.delete(keys);
        return true;
    }

    public Set<String> keys(String keyPattern){
        return redisTemplate.keys(keyPattern);
    }
    /** Common end */
}
