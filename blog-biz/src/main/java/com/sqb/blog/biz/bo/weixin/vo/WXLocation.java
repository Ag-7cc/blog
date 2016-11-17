package com.sqb.blog.biz.bo.weixin.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 地理位置实体
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
public class WXLocation {

    /**
     * 地理位置维度
     */
    @XStreamAlias("Location_X")
    private String location_X;

    /**
     * 地理位置经度
     */
    @XStreamAlias("Location_Y")
    private String location_Y;

    /**
     * 地图缩放大小
     */
    @XStreamAlias("Scale")
    private String scale;


    /**
     * 地理位置信息
     */
    @XStreamAlias("Label")
    private String label;


    public WXLocation() {
    }

    public WXLocation(String location_X, String location_Y,String scale, String label) {
        this.location_X = location_X;
        this.label = label;
        this.scale = scale;
        this.location_Y = location_Y;
    }

    public String getLocation_X() {
        return location_X;
    }

    public void setLocation_X(String location_X) {
        this.location_X = location_X;
    }

    public String getLocation_Y() {
        return location_Y;
    }

    public void setLocation_Y(String location_Y) {
        this.location_Y = location_Y;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
