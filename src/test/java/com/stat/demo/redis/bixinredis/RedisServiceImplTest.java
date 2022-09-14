package com.stat.demo.redis.bixinredis;

import com.stat.demo.BaseTest;
import com.stat.demo.util.SleepUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@Slf4j
public class RedisServiceImplTest extends BaseTest {

    @Resource
    private RedisService redisService;

    @Test
    public void buildLock() {
        String lockKey = "lockKey_123";
        RedisService.RedisLocker locker = redisService.buildLock(lockKey, 3, TimeUnit.SECONDS);
        RedisService.RedisLocker locker2 = redisService.buildLock(lockKey, 3, TimeUnit.SECONDS);
        if (!locker.tryLock()) {
            log.error("buildLock tryLock failed, lockKey={}", locker.getKey());
            return;
        }
//        SleepUtil.sleepSeconds(4);
        System.out.println("locker2.tryLock():" + locker2.tryLock());
        System.out.println("locker2.release():" + locker2.release());
        System.out.println("locker.release():" + locker.release());
    }

    public void threadPool(){
        String lockKey = "lockKey_123";
        new Thread(()->{
            RedisService.RedisLocker locker = redisService.buildLock(lockKey, 3, TimeUnit.SECONDS);
            System.out.println("ThreadA"+locker.tryLock());
        }).start();
        new Thread(()->{
            RedisService.RedisLocker locker = redisService.buildLock(lockKey, 3, TimeUnit.SECONDS);
            System.out.println("ThreadA"+locker.tryLock());
        });
    }
    
    @Test
    public void releaseLock(){
        String lockKey = "name";
        RedisService.RedisLocker locker = redisService.buildLock(lockKey, 3, TimeUnit.SECONDS);
        boolean tryLockFlag = locker.tryLock();
        System.out.println("加锁"+tryLockFlag);
        boolean releaseFlag = locker.release();
        System.out.println("解锁"+releaseFlag);

    }

    @Test
    public void testJedis() {
        JedisPool jedisPool = new JedisPool("localhost", 6379);

        Jedis jedis = jedisPool.getResource();
        String result = jedis.set("name", "yulu");
        System.out.println(result);
    }
}