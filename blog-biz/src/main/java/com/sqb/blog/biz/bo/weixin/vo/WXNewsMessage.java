package com.sqb.blog.biz.bo.weixin.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信响应图文
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
@XStreamAlias("xml")
public class WXNewsMessage extends WXBaseMessage {

    private WXNews news;

    public WXNews getNews() {
        return news;
    }

    public void setNews(WXNews news) {
        this.news = news;
    }
}
