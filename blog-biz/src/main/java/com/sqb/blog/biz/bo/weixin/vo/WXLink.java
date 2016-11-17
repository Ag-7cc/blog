package com.sqb.blog.biz.bo.weixin.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 链接实体
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
public class WXLink {

    /**
     * 消息标题
     */
    @XStreamAlias("Title")
    private String title;

    /**
     * 消息描述
     */
    @XStreamAlias("Description")
    private String description;

    /**
     * 消息链接
     */
    @XStreamAlias("Url")
    private String url;



    public WXLink() {
    }

    public WXLink( String title, String description,String url) {
        this.url = url;
        this.description = description;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
