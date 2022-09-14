package com.stat.demo.redis;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yulu
 */
public class RedisKeys {

    /**
     * 作者名称
     */
    public static final String AUTHOR_NAME_PREFIX = "author_name_";
    public static final String ORDER_LOCK_KEY = "order_lock_key_";

    public static String getAuthorNameKey(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name not empty");
        }
        return AUTHOR_NAME_PREFIX + name;
    }

    public static String getOrderLockKey(String orderId) {
        if (StringUtils.isBlank(orderId)) {
            throw new IllegalArgumentException("orderId not empty");
        }
        return ORDER_LOCK_KEY + orderId;
    }
}
