package com.sqb.blog.biz.service;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

/**
 * 微信service
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
@Service
public class WeiXinService {
    private static final Logger log = LoggerFactory.getLogger(WeiXinService.class);

    public String message(HttpServletRequest request) {
//        WXMessageLog messageLog = WeixinCenterClient.parseMessage(request);
        try (InputStream inputStream = request.getInputStream()) {
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
        }
        return "";
    }
}
