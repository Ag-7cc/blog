package com.sqb.blog.biz.service;

import com.sqb.blog.biz.bo.weixin.WXMessageLog;
import com.sqb.blog.biz.bo.weixin.vo.WXBaseMessage;
import com.sqb.blog.biz.bo.weixin.vo.WXText;
import com.sqb.blog.biz.bo.weixin.vo.WXTextMessage;
import com.sqb.blog.biz.client.WeixinCenterClient;
import com.sqb.blog.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信service
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
@Service
public class WeiXinService {
    private static final Logger log = LoggerFactory.getLogger(WeiXinService.class);

    public String message(HttpServletRequest request) {
       /* try (InputStream inputStream = request.getInputStream()) {
            //读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            //noinspection unchecked
            List<Element> elementList = root.elements();
            for (Element e : elementList) {
                log.info("name={},text={}", e.getName(), e.getText());
            }
            //wWgCYweXWSC7s9l2jycAiuVipCnFnlMHrgApwF7p4G3
            // 遍历所有子节点
        } catch (Exception e) {
            log.error("", e);
        }*/

        WXMessageLog messageLog = WeixinCenterClient.parseMessage(request);
        System.out.println(JsonUtil.toString(messageLog));
        sendWXTextMessage(messageLog.getUserName(),"哈哈，可以了");
        return "";
    }

    /**
     * 发送一条微信文本消息
     *
     * @param openId
     * @param text
     */
    public void sendWXTextMessage(String openId, String text) {
        // 发送消息
        WXTextMessage wxMsg = new WXTextMessage();
        wxMsg.setToUserName(openId);
        wxMsg.setText(new WXText(text));
        sendWXMessage(wxMsg);
    }

    /**
     * 发送微信消息
     *
     * @param wxMsg
     */
    private void sendWXMessage(WXBaseMessage wxMsg) {
        WeixinCenterClient.messageCustomSend(wxMsg);
    }
}
