package com.sqb.blog.biz.bo.weixin.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 响应图片实体
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
public class WXImage {

    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
     */
    @XStreamAlias("MediaId")
    private String mediaId;
    /**
     * 图片链接
     */
    @XStreamAlias("PicUrl")
    private String picUrl;
    /**
     * 图片服务器地址
     */
    private String imgSerPath;
    /**
     * 图片MD5，用于生成预诊订单消息时的客户端路径
     */
    private String imgMD5;

    // ========== 非存储字段 ==========
    /**
     * 图片文件二进制数组
     */
    private byte[] imgBytes;

    public WXImage() {
    }

    public WXImage(String mediaId) {
        this.mediaId = mediaId;
    }

    public WXImage(String mediaId,String picUrl) {
        this.mediaId = mediaId;
        this.picUrl=picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getImgSerPath() {
        return imgSerPath;
    }

    public void setImgSerPath(String imgSerPath) {
        this.imgSerPath = imgSerPath;
    }

    public String getImgMD5() {
        return imgMD5;
    }

    public void setImgMD5(String imgMD5) {
        this.imgMD5 = imgMD5;
    }

    public byte[] getImgBytes() {
        return imgBytes;
    }

    public void setImgBytes(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }
}
