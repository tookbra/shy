package com.tookbra.shy.controller;

import com.tookbra.shy.domain.Page;
import com.tookbra.shy.domain.ShyTopic;
import com.tookbra.shy.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by tookbra on 2016/6/18.
 */
@Controller
public class HomeController {

    @Autowired
    TopicService topicService;

    @RequestMapping("/")
    public String index(Page page, ModelMap modelMap) {
        List<ShyTopic> list = topicService.fingPage(page);
        modelMap.put("shyTopic", list);
        return "home/index";
    }

    @RequestMapping("page/{pageIndex}")
    public String flip(@PathVariable Integer pageIndex, ModelMap modelMap) {
        Page page = new Page();
        page.setPage(pageIndex);
        List<ShyTopic> list = topicService.fingPage(page);
        modelMap.put("shyTopic", list);
        return "home/index";
    }
}
