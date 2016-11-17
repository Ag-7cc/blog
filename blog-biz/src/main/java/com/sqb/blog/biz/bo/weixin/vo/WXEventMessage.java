package com.sqb.blog.biz.bo.weixin.vo;

import com.sqb.blog.biz.client.WeixinCenterClient;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信事件消息
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
@XStreamAlias("xml")
public class WXEventMessage extends WXBaseMessage {

    @XStreamAlias("Location")
    private WXLocation location;

    public WXEventMessage() {
        setMsgType(WeixinCenterClient.MESSAGE_TYPE_EVENT);
    }

    public WXLocation getLocation() {
        return location;
    }

    public void setLocation(WXLocation location) {
        this.location = location;
    }
}
