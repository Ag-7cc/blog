package com.sqb.blog.biz.bo.weixin.vo;

import com.sqb.blog.biz.client.WeixinCenterClient;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信视频消息
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
@XStreamAlias("xml")
public class WXVideoMessage extends WXBaseMessage {

    @XStreamAlias("Voice")
    private WXVoice voice;

    public WXVideoMessage() {
        setMsgType(WeixinCenterClient.MESSAGE_TYPE_VIDEO);
    }

    public WXVoice getVoice() {
        return voice;
    }

    public void setVoice(WXVoice voice) {
        this.voice = voice;
    }
}
