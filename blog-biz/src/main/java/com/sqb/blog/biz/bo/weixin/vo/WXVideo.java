package com.sqb.blog.biz.bo.weixin.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 视频实体
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
public class WXVideo {

    @XStreamAlias("MediaId")
    private String mediaId;

    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;

    public WXVideo() {
    }

    public WXVideo(String mediaId, String thumbMediaId) {
        this.mediaId = mediaId;
        this.thumbMediaId=thumbMediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}
