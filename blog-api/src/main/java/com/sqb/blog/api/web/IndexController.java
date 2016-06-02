package com.sqb.blog.api.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by vic.
 * Copyright 2016 by vic.shan
 * Date: 2016/5/30 23:51
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
