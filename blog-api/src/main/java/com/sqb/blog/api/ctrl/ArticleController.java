package com.sqb.blog.api.ctrl;

import com.sqb.blog.api.view.View;
import com.sqb.blog.api.view.ViewBody;
import com.sqb.blog.api.view.ViewUtil;
import com.sqb.blog.biz.bo.Article;
import com.sqb.blog.biz.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 日志
 * Created by vic.
 * Copyright 2016 by vic.shan
 * Date: 2016/5/31 22:27
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 获取日志列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public View getArticleList(@PathVariable("userId") Long userId) {
        List<Article> articleList = articleService.getArticleListByUid(userId);
        return ViewUtil.defaultView().setBody(new ViewBody(articleList));
    }
}
