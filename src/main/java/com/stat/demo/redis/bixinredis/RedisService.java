package com.stat.demo.redis.bixinredis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public interface RedisService {

    /**
     * 设置缓存过期时间
     *
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time);

    /**
     * 设置hash类型缓存某个子项的值
     *
     * @param key
     * @param item
     * @param value
     * @param time
     * @return
     */
    public boolean hset(String key, String item, Object value, long time);

    /**
     * 获取hash类型缓存的某个子项
     *
     * @param key
     * @param item
     * @return
     */
    public Object hget(String key, String item);

    /**
     * 检查hash类型缓存是否存在指定子项
     *
     * @param key
     * @param item
     * @return
     */
    public boolean hHasKey(String key, String item);

    /**
     * set类型缓存递增
     *
     * @param key
     * @param item
     * @param by
     * @return
     * @throws
     */
    public double hincr(String key, String item, double by) throws RuntimeException;

    /**
     * 获取一个锁对象
     *
     * @param key      锁key
     * @param expire   锁过期时间
     * @param timeUnit 过期单位
     * @return 锁对象
     */
    RedisLocker buildLock(String key, long expire, TimeUnit timeUnit);

    /**
     * 获取RedisTemplate对象，可以通过该对象调用未被RedisService暴露出来的redis方法
     *
     * @return RedisTemplate
     */
    RedisTemplate<String, Object> getRedisTemplate();

    interface RedisLocker {

        boolean tryLock();

        boolean release();

        Object getKey();
    }

}
