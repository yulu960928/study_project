package com.stat.demo.redis;

import com.stat.demo.BaseTest;
import com.stat.demo.util.SleepUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisManagerTest extends BaseTest {

    @Resource
    private RedisManager redisManager;


    @Test
    public void setAndGetKey() {
        String yuluZhName = "yulu";
        redisManager.setKey(RedisKeys.getAuthorNameKey(yuluZhName), "俞璐");
        String value = redisManager.getKey(RedisKeys.getAuthorNameKey(yuluZhName));
        System.out.println(yuluZhName + ":" + value);
    }

    @Test
    public void lock() {
        String orderId = "N123412";
        boolean lockSuccess = redisManager.lock(RedisKeys.getOrderLockKey(orderId), 3);
        if (!lockSuccess) {
            log.error("order lock failed orderId={}", orderId);
            return;
        }
        try {
            long sleepSeconds = 2;
            // placeOrder
            SleepUtil.sleepSeconds(sleepSeconds);
            log.info("placeOrder success");
        } finally {
            boolean unlockSuccess = redisManager.unlock(RedisKeys.getOrderLockKey(orderId));
            if (!unlockSuccess) {
                log.error("order unlock failed orderId={}", orderId);
            }
        }
    }

    @Test
    public void unlock() {
    }
}