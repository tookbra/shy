package com.tookbra.shy.services;

import com.tookbra.shy.dao.TopicDao;
import com.tookbra.shy.domain.Page;
import com.tookbra.shy.domain.ShyTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by tookbra on 2016/6/19.
 */
@Service
public class TopicService {
    private static final Logger logger = LoggerFactory.getLogger(TopicService.class);

    @Autowired
    TopicDao topicDao;

    public void test(Page page) {
        topicDao.pageByTopic(page);
    }

    public List<ShyTopic> fingPage(Page page) {
//        Assert.notNull(null);
        return topicDao.pageByTopic(page);
    }
}
