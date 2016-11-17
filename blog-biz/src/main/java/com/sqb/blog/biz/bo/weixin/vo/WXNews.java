package com.sqb.blog.biz.bo.weixin.vo;

import java.util.List;

/**
 * 微信图文
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
public class WXNews {

    /**
     * 图文元素数量
     */
    private Integer articleCount;
    /**
     * 图文数组
     */
    private List<WXItem> articles;

    public WXNews() {
    }

    public WXNews(List<WXItem> articles) {
        this.articles = articles;
    }

    public List<WXItem> getArticles() {
        return articles;
    }

    public void setArticles(List<WXItem> articles) {
        this.articles = articles;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }
}
