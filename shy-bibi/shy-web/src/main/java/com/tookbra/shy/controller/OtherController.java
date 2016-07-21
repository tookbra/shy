package com.tookbra.shy.controller;

import com.tookbra.shy.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tookbra on 2016/7/19.
 */
@RestController
public class OtherController {

    @Autowired
    TopicService topicService;

    @RequestMapping(method = RequestMethod.PUT, value = "likes/{id}")
    public Long updateLikes(@PathVariable String id) {
        Long count =  topicService.updateLikes(id);
        return count;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "views/{id}")
    public Long updateView(@PathVariable String id) {
        Long count =  topicService.updateViews(id);
        return count;
    }
}
