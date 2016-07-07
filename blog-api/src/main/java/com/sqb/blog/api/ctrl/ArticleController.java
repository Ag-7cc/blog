package com.sqb.blog.api.ctrl;

import com.sqb.blog.api.view.View;
import com.sqb.blog.api.view.ViewBody;
import com.sqb.blog.api.view.ViewUtil;
import com.sqb.blog.biz.bo.Article;
import com.sqb.blog.biz.service.ArticleService;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(method = RequestMethod.GET)
    public View getArticleList(@RequestParam(value = "userId",required = false) Long userId) {
        Assert.notNull(userId);
        List<Article> articleList = articleService.getArticleListByUid(userId);
        return ViewUtil.defaultView().setBody(new ViewBody(articleList));
    }

    /**
     * 获取推荐日志列表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/recomment")
    public View getRecommentArticleList(){
        List<Article> articleList = articleService.getRecommentArticleList();
        return ViewUtil.defaultView().setBody(new ViewBody(articleList));
    }

    /**
     * 根据id查询日志详细信息
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public View getArticle(@PathVariable("id")Long id){
        Article article = articleService.getArticleListById(id);
        return ViewUtil.defaultView().setBody(new ViewBody(article));
    }
}
