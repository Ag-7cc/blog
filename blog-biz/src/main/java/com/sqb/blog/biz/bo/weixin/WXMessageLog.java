package com.sqb.blog.biz.bo.weixin;

import com.sqb.blog.biz.bo.weixin.vo.*;

/**
 * 微信消息
 * Created by vic.shan
 * Date: 2016/11/17.16:10
 */
public class WXMessageLog {
    /**
     * 来源类型 - 发送
     */
    public static final Integer FROM_TYPE_SEND = 1;
    /**
     * 来源类型 - 接收
     */
    public static final Integer FROM_TYPE_RECEIVE = 2;

    /**
     * 微信消息编号
     */
    private String msgId;
    /**
     * 用户编号
     */
    private Integer uid;
    /**
     * 用户帐号
     */
    private String userName;
    /**
     * 名字
     */
    private String name;
    /**
     * 来源类型
     */
    private Integer fromType;
    /**
     * 消息类型
     */
    private String msgType;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 文本内容
     */
    private WXText text;
    /**
     * 图片内容
     */
    private WXImage image;
    /**
     * 语音内容
     */
    private WXVoice voice;
    /**
     * 视频内容
     */
    private WXVideo video;
    /**
     * 地理位置
     */
    private WXLocation location;
    /**
     * 地理位置
     */
    private WXLink link;
    /**
     * 事件
     */
    private WXEvent event;
    /**
     * 图文
     */
    private WXNews news;
    /**
     * 加密串
     */
    private String encrypt;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public WXVoice getVoice() {
        return voice;
    }

    public void setVoice(WXVoice voice) {
        this.voice = voice;
    }

    public WXImage getImage() {
        return image;
    }

    public void setImage(WXImage image) {
        this.image = image;
    }

    public WXNews getNews() {
        return news;
    }

    public void setNews(WXNews news) {
        this.news = news;
    }

    public WXText getText() {
        return text;
    }

    public void setText(WXText text) {
        this.text = text;
    }

    public WXVideo getVideo() {
        return video;
    }

    public void setVideo(WXVideo video) {
        this.video = video;
    }

    public WXLocation getLocation() {
        return location;
    }

    public void setLocation(WXLocation location) {
        this.location = location;
    }

    public WXLink getLink() {
        return link;
    }

    public void setLink(WXLink link) {
        this.link = link;
    }

    public WXEvent getEvent() {
        return event;
    }

    public void setEvent(WXEvent event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }
}
