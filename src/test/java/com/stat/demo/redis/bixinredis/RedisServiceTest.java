package com.stat.demo.redis.bixinredis;

import com.stat.demo.BaseTest;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.swing.*;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class RedisServiceTest extends BaseTest {

    @Resource
    private RedisService redisService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public final static String KEY = "cp:123";
    public final static String FIELD = "times";
    public final static int TIME = 123;

    @Test
    public void testHset() {
        //单位秒
        boolean exists = redisService.hHasKey(KEY, FIELD);
        if (!exists) {
            redisService.hset(KEY, FIELD, 1L, 1);
        }
//        double times = redisService.hincr(KEY, FIELD, 1L);
//        if (times > 3) {
//            System.out.println("发布太频繁了");
//        }else {
//            System.out.println("发布成功");
//        }

    }

    @Test
    public void enter() {
        for (int i = 0; i < 10; i++) {
            boolean success = tryLock(KEY);
            if (!success) {
                throw new RuntimeException("太多了");
            }
        }
    }

    private boolean tryLock(String key) {
        Boolean exists = redisTemplate.hasKey(key);
        if (Boolean.FALSE.equals(exists)) {
            redisTemplate.opsForValue().set(key, "1");
            redisTemplate.expire(key, 60, TimeUnit.SECONDS);
            System.out.println("正常");
            return true;
        }
        Long number = redisTemplate.opsForValue().increment(key, 1L);
        if (null != number && number > 3) {
            System.out.println("太多了");
            return false;
        }
        System.out.println("正常");
        return true;
    }

    @Test
    public void test2() {
        tryLock(KEY, 1, 5,2);
        return;
    }

    private boolean tryLock(String key, long delta, long expireMinutes,int times) {
//        key: key_{yyyyMMdd}
//        value: 1
//        expire: 1 d
        // 1-num 2-num 3-num 4-num 5-num  最近5分钟

//        key: key_{yyyyMMdd HHmm}
//        value: 1
//        expire: 1 d
        Long increment = redisTemplate.opsForValue().increment(key, delta);
        if (increment != null && increment.equals(1L)) {
            redisTemplate.expire(key, expireMinutes, TimeUnit.MINUTES);
        }
        return increment != null && increment <= times;
    }

}