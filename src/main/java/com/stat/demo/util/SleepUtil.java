package com.stat.demo.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author yulu
 */
@Slf4j
public class SleepUtil {

    public static void sleepSeconds(long sleepSeconds) {
        try {
            TimeUnit.SECONDS.sleep(sleepSeconds);
        } catch (Exception e) {
            log.error("sleepSeconds error, sleepSeconds={}", sleepSeconds, e);
        }
    }
}
