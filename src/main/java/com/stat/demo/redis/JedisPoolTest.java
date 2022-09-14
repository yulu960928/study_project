package com.stat.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author yulu
 */
public class JedisPoolTest {

    public static void main(String[] args) {
//        testJedisPool();
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.auth("960928");
        jedis.set("msg", "Hello World!");
        String msg = jedis.get("msg");
        System.out.println(msg);
        jedis.close();
    }

    private static void testJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(config, "localhost", 6379, 3000, "960928");
        Jedis jedis = jedisPool.getResource();
//        String result = jedis.set("name","yulu");
        String result = jedis.get("name");
        System.out.println(result);
        jedisPool.close();
    }
}
