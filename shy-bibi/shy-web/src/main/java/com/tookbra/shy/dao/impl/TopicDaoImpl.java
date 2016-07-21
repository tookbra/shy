package com.tookbra.shy.dao.impl;

import com.google.common.collect.Lists;
import com.tookbra.shy.dao.support.BaseRedisDao;
import com.tookbra.shy.dao.TopicDao;
import com.tookbra.shy.domain.Page;
import com.tookbra.shy.domain.ShyTopic;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by tookbra on 2016/6/22.
 */
@Repository
public class TopicDaoImpl extends BaseRedisDao<String, ShyTopic> implements TopicDao {

    public ShyTopic get(String key) {
        ValueOperations<String, ShyTopic> valueops = redisTemplate.opsForValue();
        ShyTopic topic = valueops.get(key);
        return topic;
    }

    /**
     *  Cannot deserialize; nested exception is org.springframework.core.serializer.support.SerializationFailedException:
     *  Failed to deserialize payload.
     *  need to set redistemplate ValueSerializer
     */
    public List<ShyTopic> pageByTopic(Page page) {
        logger.info("query from redis.pageStart:{}, limit:{}", page.getStart(), page.getEnd());
        Set<ShyTopic> topicSet = redisTemplate.opsForZSet().reverseRange("topicId", page.getStart(), page.getEnd());
        List<ShyTopic> shyTopicList =  Lists.newArrayList(topicSet);
        return shyTopicList;
    }

    public Long increment(final String key) {
        ValueOperations operations= redisTemplate.opsForValue();
        long count = operations.increment(key, 1);
        return count;
//
    }

    public Long getIncrement(final String key) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] rowkey = serializer.serialize(key);
                byte[] rowval = redisConnection.get(rowkey);
                try {
                    String val = serializer.deserialize(rowval);
                    return Long.parseLong(val);
                } catch (Exception e) {
                    return 0L;
                }
            }
        });
    }
}
