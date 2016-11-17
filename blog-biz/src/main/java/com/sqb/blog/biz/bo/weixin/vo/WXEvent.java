package com.sqb.blog.biz.bo.weixin.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 事件实体
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
public class WXEvent {

    /**
     * 事件名
     */
    @XStreamAlias("Event")
    private String event;

    /**
     * 事件key
     */
    @XStreamAlias("Eventkey")
    private String eventKey;


    /**
     * 首次关注时生成
     */
    @XStreamAlias("Ticket")
    private String ticket;




    public WXEvent() {
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public WXEvent(String event, String eventKey ,String ticket) {
        this.event = event;
        this.eventKey = eventKey;
        this.ticket = ticket;
    }
}
