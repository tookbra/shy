package com.tookbra.shy.dao.impl;

import com.google.common.collect.Lists;
import com.tookbra.shy.dao.support.BaseRedisDao;
import com.tookbra.shy.dao.TopicDao;
import com.tookbra.shy.domain.Page;
import com.tookbra.shy.domain.ShyTopic;
import org.springframework.data.redis.core.ValueOperations;
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
        for (ShyTopic shyTopic : shyTopicList) {
            List<String> imgList = Lists.newArrayList();
            for(String img :shyTopic.getImgs()) {
                if(img.startsWith("https://")) {
                    img = img.replace("https://", "http://");
                }
                img = img.replace(".doubanio.com", ".douban.com").replace("img1", "img3");
                imgList.add(img);
            }
            shyTopic.setImgs(imgList.toArray(new String[shyTopic.getImgs().length]));
        }
        return shyTopicList;
    }
}
