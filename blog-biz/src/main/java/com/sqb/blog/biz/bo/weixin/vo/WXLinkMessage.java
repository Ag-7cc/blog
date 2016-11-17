package com.sqb.blog.biz.bo.weixin.vo;

import com.sqb.blog.biz.client.WeixinCenterClient;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信链接消息
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
@XStreamAlias("xml")
public class WXLinkMessage extends WXBaseMessage {

    @XStreamAlias("link")
    private WXLink link;

    public WXLinkMessage() {
        setMsgType(WeixinCenterClient.MESSAGE_TYPE_LINK);
    }

    public WXLink getLink() {
        return link;
    }

    public void setLink(WXLink link) {
        this.link = link;
    }
}
