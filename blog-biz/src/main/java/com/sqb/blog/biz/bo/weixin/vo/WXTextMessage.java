package com.sqb.blog.biz.bo.weixin.vo;

import com.sqb.blog.biz.client.WeixinCenterClient;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信响应文本消息
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
@XStreamAlias("xml")
public class WXTextMessage extends WXBaseMessage {

    /**
     * 消息文本
     */
    private WXText text;

    public WXTextMessage() {
        setMsgType(WeixinCenterClient.MESSAGE_TYPE_TEXT);
    }

    public WXText getText() {
        return text;
    }

    public void setText(WXText text) {
        this.text = text;
    }
}
