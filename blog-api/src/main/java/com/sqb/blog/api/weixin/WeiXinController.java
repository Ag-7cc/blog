package com.sqb.blog.api.weixin;

import com.sqb.blog.api.cmpt.CtrlAspect;
import com.sqb.blog.util.AppContext;
import com.sqb.blog.util.DigestUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 微信公众号开发接口
 * Created by vic.shan
 * Date: 2016/9/13.14:46
 */
@Controller
@RequestMapping("/wx")
public class WeiXinController {
    private static final Logger log = LoggerFactory.getLogger(WeiXinController.class);

    /**
     * 校验来自微信服务器的请求
     *
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   随机字符串
     * @throws Exception
     */
    @RequestMapping(value = "/weiXinInterface", method = RequestMethod.GET)
    public void weiXinInterface(HttpServletResponse response,
                                @RequestParam(value = "signature") String signature,
                                @RequestParam(value = "timestamp") String timestamp,
                                @RequestParam(value = "nonce") String nonce,
                                @RequestParam(value = "echostr") String echostr) throws Exception {
        // 将token、timestamp、nonce三个参数进行字典序排序
        String[] strArray = new String[]{AppContext.getProperty("weixin.token"), timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder content = new StringBuilder();
        for (String s : strArray) {
            content.append(s);
        }
        //将三个参数字符串拼接成一个字符串进行sha1加密，并与signature对比,如果相同返回echostr
        if (DigestUtil.digest(content.toString(), DigestUtil.SHA1).equals(signature)) {
            response.getWriter().print(echostr);
        }
    }

    /**
     * 处理所有请求
     *
     * @throws Exception
     */
    @RequestMapping(value = "/weiXinInterface", method = RequestMethod.POST)
    @ResponseBody
    public void weiXinInterface(HttpServletRequest request) throws Exception {
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
            // 遍历所有子节点
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
