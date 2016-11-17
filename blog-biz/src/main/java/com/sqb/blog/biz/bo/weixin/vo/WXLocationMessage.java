package com.sqb.blog.biz.bo.weixin.vo;

import com.sqb.blog.biz.client.WeixinCenterClient;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信地理位置消息
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
@XStreamAlias("xml")
public class WXLocationMessage extends WXBaseMessage {

    @XStreamAlias("Location")
    private WXLocation location;

    public WXLocationMessage() {
        setMsgType(WeixinCenterClient.MESSAGE_TYPE_LOCATION);
    }

    public WXLocation getLocation() {
        return location;
    }

    public void setLocation(WXLocation location) {
        this.location = location;
    }
}
