package com.tookbra.shy.dao.support;

import com.tookbra.shy.domain.ShyTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;

/**
 * Created by tookbra on 2016/6/22.
 */
public abstract class BaseRedisDao<K extends Serializable,V extends Serializable> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RedisTemplate<K,V> redisTemplate;

    /**
     * @param redisTemplate the redisTemplate to set
     */
    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisTemplate<K, V> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * RedisSerializer
     */
    protected RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }

    protected RedisSerializer<ShyTopic> getVRedisSerializer() {
        return (RedisSerializer<ShyTopic>) redisTemplate.getValueSerializer();
    }


    public void sendMessage(String channel, Serializable message) {
        redisTemplate.convertAndSend(channel, message);
    }
}
