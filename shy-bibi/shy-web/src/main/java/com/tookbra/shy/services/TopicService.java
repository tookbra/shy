package com.tookbra.shy.services;

import com.google.common.collect.Lists;
import com.tookbra.shy.common.Constant;
import com.tookbra.shy.dao.TopicDao;
import com.tookbra.shy.domain.Page;
import com.tookbra.shy.domain.ShyTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

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
        List<ShyTopic> shyTopicList =  topicDao.pageByTopic(page);
        for (ShyTopic shyTopic : shyTopicList) {
            List<String> imgList = Lists.newArrayList();
            for(String img :shyTopic.getImgs()) {
                if(img.startsWith("https://")) {
                    img = img.replace("https://", "http://");
                }
                img = img.replace("img1", "img3");
                imgList.add(img);
            }
            shyTopic.setImgs(imgList.toArray(new String[shyTopic.getImgs().length]));
            shyTopic.setLikes(topicDao.getIncrement(Constant.SHY_LIKES.concat(shyTopic.getId().toString())));
            shyTopic.setViews(topicDao.getIncrement(Constant.SHY_VIEWS.concat(shyTopic.getId().toString())));
        }
        return shyTopicList;
    }

    public Long updateLikes(String id) {
        return topicDao.increment(Constant.SHY_LIKES.concat(id));
    }

    public Long updateViews(String id) {
        return topicDao.increment(Constant.SHY_VIEWS.concat(id));
    }
}
