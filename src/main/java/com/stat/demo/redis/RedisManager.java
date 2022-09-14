package com.stat.demo.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yulu
 */
@Component
public class RedisManager {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public void setKey(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean lock(String lockKey, long expireSeconds) {
        // set nx key value
        Boolean lockSuccess = redisTemplate.opsForValue().setIfAbsent(lockKey, "success");
        if (!Boolean.TRUE.equals(lockSuccess)) {
            return false;
        }
        Boolean expireSuccess = redisTemplate.expire(lockKey, expireSeconds, TimeUnit.SECONDS);
        if (!Boolean.TRUE.equals(expireSuccess)) {
            return false;
        }
        return true;
    }

    public boolean unlock(String lockKey) {
        return redisTemplate.delete(lockKey);
    }


}
