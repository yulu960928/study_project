package com.stat.demo.redis.bixinredis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author yulu
 */
@Component
@Slf4j
public class RedisServiceImpl implements RedisService {

    private static final RedisScript<String> SCRIPT_LOCK =
            new DefaultRedisScript<>("return redis.call('set',KEYS[1],ARGV[1],'NX','PX',ARGV[2])", String.class);
    private static final RedisScript<String> SCRIPT_UNLOCK =
            new DefaultRedisScript<>("if redis.call('get',KEYS[1]) == ARGV[1] then return tostring(redis.call('del',KEYS[1])==1) else return 'false' end", String.class);
    private static final String LOCK_SUCCESS = "OK";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void setKey(String key, String args, Long expireMills) {
        //jedis.set(key, args, "NX", "PX", expireMills);
    }

    @Override
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("设置缓存过期时间失败: {}", key, e);
            return false;
        }
    }

    @Override
    public boolean hset(String key, String item, Object value, long time) {
        try {
            return redisTemplate.execute(new SessionCallback<Boolean>() {
                @Override
                public Boolean execute(RedisOperations operations) throws DataAccessException {
                    operations.multi();
                    operations.opsForHash().put(key, item, value);
                    expire(key, time);
                    operations.exec();
                    return true;
                }
            });
        } catch (Exception e) {
            log.error("设置hash类型缓存某个子项的值失败: {}, hashKey:{}", key, item, e);
            return false;
        }
    }

    @Override
    public Object hget(String key, String item) {
        try {
            return redisTemplate.opsForHash().get(key, item);
        } catch (Exception e) {
            log.error("获取Hash类型缓存的某个子项失败: " + key, e);
        }
        return null;
    }

    @Override
    public boolean hHasKey(String key, String item) {
        try {
            return redisTemplate.opsForHash().hasKey(key, item);
        } catch (Exception e) {
            log.error("检查hash类型缓存是否存在指定子项失败: {}", key, e);
        }
        return false;
    }

    @Override
    public double hincr(String key, String item, double by) {
        try {
            return redisTemplate.opsForHash().increment(key, item, by);
        } catch (Exception e) {
            throw new RuntimeException("Hash类型缓存递增失败: " + key + ", hashKey: " + item, e);
        }

    }

    @Override
    public RedisLocker buildLock(String key, long expire, TimeUnit timeUnit) {
        return new RedisLockImpl(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    private class RedisLockImpl implements RedisLocker {

        private String key;
        private String value;
        private long expireTime;

        protected RedisLockImpl(String key, long expireTime, TimeUnit timeUnit) {
            this.key = key;
            this.value = UUID.randomUUID().toString();
            this.expireTime = expireTime;
        }

        @Override
        public boolean tryLock() {
            //底层：set key value nx px time :key不存在时设置值为value,过期时间为time毫秒
            // 或者  set key value nx ex time :key不存在时设置值为value,过期时间为time秒
            //底层通过lua脚本实现,成功则返回true
            String result = redisTemplate.execute(SCRIPT_LOCK,
                    redisTemplate.getStringSerializer(),
                    redisTemplate.getStringSerializer(),
                    Collections.singletonList(key),
                    value, String.valueOf(expireTime));
            if (LOCK_SUCCESS.equals(result)) {
                return true;
            }
            return false;
        }

        @Override
        public synchronized boolean release() {
            String result = redisTemplate.execute(SCRIPT_UNLOCK,
                    redisTemplate.getStringSerializer(),
                    redisTemplate.getStringSerializer(),
                    Collections.singletonList(key),
                    value);
            log.info("release key={}, result={}", getKey(), result);
            return "true".equals(result);
        }

        @Override
        public Object getKey() {
            return key;
        }
    }


}


