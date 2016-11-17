package com.sqb.blog.biz.bo.weixin.vo;

import com.sqb.blog.biz.client.WeixinCenterClient;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信响应图片消息
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
@XStreamAlias("xml")
public class WXImageMessage extends WXBaseMessage {

    @XStreamAlias("Image")
    private WXImage image;

    public WXImageMessage() {
        setMsgType(WeixinCenterClient.MESSAGE_TYPE_IMAGE);
    }

    public WXImage getImage() {
        return image;
    }

    public void setImage(WXImage image) {
        this.image = image;
    }
}
