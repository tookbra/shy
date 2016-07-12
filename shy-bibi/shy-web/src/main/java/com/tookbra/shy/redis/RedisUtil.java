package com.tookbra.shy.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis tool util
 * Created by tookbra on 2016/6/19.
 */
public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    public static JedisPool jedisPool = null;

    /**
     * 返还到连接池
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            try {
                jedis.close();
            } catch (Exception e) {
                logger.warn("Release jedis resource error.", e);
            }
        }
    }
}
