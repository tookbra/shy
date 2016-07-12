package com.tookbra.shy;

import com.tookbra.shy.redis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import redis.clients.jedis.JedisPool;

/**
 * Created by tookbra on 2016/6/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class RedisTest {



    @Test
    public void redisTest() {
        JedisPool jedisPool = RedisUtil.jedisPool;
        System.out.println(1);
    }
}
