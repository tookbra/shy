package com.tookbra.shy.dao;

import com.tookbra.shy.domain.Page;
import com.tookbra.shy.domain.ShyTopic;

import java.util.List;

/**
 * Created by tookbra on 2016/6/22.
 */
public interface TopicDao {

    List<ShyTopic> pageByTopic(Page page);
}
