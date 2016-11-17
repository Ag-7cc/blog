package com.sqb.blog.biz.bo.weixin.vo;

import com.sqb.blog.biz.client.WeixinCenterClient;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信响应语音消息
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
@XStreamAlias("xml")
public class WXVoiceMessage extends WXBaseMessage {

    @XStreamAlias("Voice")
    private WXVoice voice;

    public WXVoiceMessage() {
        setMsgType(WeixinCenterClient.MESSAGE_TYPE_VOICE);
    }

    public WXVoice getVoice() {
        return voice;
    }

    public void setVoice(WXVoice voice) {
        this.voice = voice;
    }
}
