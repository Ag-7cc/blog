package com.sqb.blog.biz.bo.weixin.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信消息基础类
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
public abstract class WXBaseMessage {

    /**
     * 接收方帐号（收到的OpenID）
     */
    @XStreamAlias("ToUserName")
    private String toUserName;
    /**
     * 开发者微信号
     */
    @XStreamAlias("FromUserName")
    private String fromUserName;
    /**
     * 消息创建时间 （整型），单位：毫秒
     */
    @XStreamAlias("CreateTime")
    private Long createTime;
    /**
     * 消息类型（text/music/news）
     */
    @XStreamAlias("MsgType")
    private String msgType;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
