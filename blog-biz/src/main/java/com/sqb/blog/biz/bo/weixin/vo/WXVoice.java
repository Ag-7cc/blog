package com.sqb.blog.biz.bo.weixin.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 语音实体
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
public class WXVoice {

    /**
     * 媒体编号
     */
    @XStreamAlias("MediaId")
    private String mediaId;
    /**
     * 格式
     */
    @XStreamAlias("Format")
    private String format;
    /**
     * 语音服务器
     */
    private String audioSerPath;
    /**
     * 语音时长
     */
    private Double audioTime;
    /**
     * 语音转换状况
     */
    private Integer audioConvertBits;
    /**
     * Speex文件MD5值，用于生成预诊订单消息时的客户端路径
     */
    private String spxMD5;
    /**
     * 语音识别结果
     */
    private String recognition;

    // ========== 非存储字段 ==========
    /**
     * spx文件二进制数组
     */
    private byte[] spxBytes;

    public WXVoice() {
    }

    public WXVoice(String mediaId) {
        this.mediaId = mediaId;
    }

    public WXVoice(String mediaId, String format, String recognition) {
        this.mediaId = mediaId;
        this.format=format;
        this.recognition = recognition;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAudioSerPath() {
        return audioSerPath;
    }

    public void setAudioSerPath(String audioSerPath) {
        this.audioSerPath = audioSerPath;
    }

    public Integer getAudioConvertBits() {
        return audioConvertBits;
    }

    public void setAudioConvertBits(Integer audioConvertBits) {
        this.audioConvertBits = audioConvertBits;
    }

    public Double getAudioTime() {
        return audioTime;
    }

    public void setAudioTime(Double audioTime) {
        this.audioTime = audioTime;
    }

    public String getSpxMD5() {
        return spxMD5;
    }

    public void setSpxMD5(String spxMD5) {
        this.spxMD5 = spxMD5;
    }

    public byte[] getSpxBytes() {
        return spxBytes;
    }

    public void setSpxBytes(byte[] spxBytes) {
        this.spxBytes = spxBytes;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }
}
