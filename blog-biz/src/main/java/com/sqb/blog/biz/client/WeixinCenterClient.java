package com.sqb.blog.biz.client;

import com.sqb.blog.biz.bo.weixin.WXMessageLog;
import com.sqb.blog.biz.bo.weixin.vo.*;
import com.sqb.blog.util.AppContext;
import com.sqb.blog.util.DateUtil;
import com.sqb.blog.util.HttpUtil;
import com.sqb.blog.util.JsonUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by vic.shan
 * Date: 2016/11/17.16:12
 */
@Component
@EnableScheduling
public class WeixinCenterClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeixinCenterClient.class);

    /**
     * ACCESS_TOKEN 失效时间，单位：秒
     */
    public static final int ACCESS_TOKEN_EXPIRED_TIME = 7200;
    /**
     * JS_API_TICKET 失效时间，单位：秒
     */
    public static final int JS_API_TICKET_EXPIRED_TIME = 7200;
    /**
     * HTTP重试次数
     */
    private static final int HTTP_RETRY_COUNT = 3;

    /**
     * 消息类型 - 文本
     */
    public static final String MESSAGE_TYPE_TEXT = "text";
    /**
     * 消息类型 - 图片
     */
    public static final String MESSAGE_TYPE_IMAGE = "image";
    /**
     * 消息类型 - 语音
     */
    public static final String MESSAGE_TYPE_VOICE = "voice";
    /**
     * 消息类型 - 视频
     */
    public static final String MESSAGE_TYPE_VIDEO = "video";
    /**
     * 消息类型 - 地理位置
     */
    public static final String MESSAGE_TYPE_LOCATION = "location";
    /**
     * 消息类型 - 链接
     */
    public static final String MESSAGE_TYPE_LINK = "link";
    /**
     * 消息类型 - 图文(响应时才有)
     */
    public static final String MESSAGE_TYPE_NEWS = "news";
    /**
     * 消息类型 - 事件(菜单点击时触发)
     */
    public static final String MESSAGE_TYPE_EVENT = "event";

    /**
     * 媒体类型 - 图片（image）: 128K，支持JPG格式
     */
    public static final String MEDIA_TYPE_IMAGE = "image";
    /**
     * 媒体类型 - 256K，播放长度不超过60s，支持AMR\MP3格式
     */
    public static final String MEDIA_TYPE_VOICE = "voice";
    /**
     * 媒体类型 - 1MB，支持MP4格式
     */
    public static final String MEDIA_TYPE_VIDEO = "video";
    /**
     * 媒体类型 - 64KB，支持JPG格式
     */
    public static final String MEDIA_TYPE_THUMB = "thumb";

    /**
     * 账号token
     */
//    public static final String TOKEN = "kangdayuzhen";

    private static final String SCHEME = "https";

    private static final String HOST = "api.weixin.qq.com/";

    private static final String HOST_MP = "mp.weixin.qq.com/";

    private static final String PATH_ACCESS_TOKEN = "cgi-bin/token";

    private static final String PATH_CREATE_MENU = "cgi-bin/menu/create";

    private static final String PATH_MENU_DELETE = "cgi-bin/menu/delete";

    private static final String PATH_CREATE_QRCODE = "cgi-bin/qrcode/create";

    private static final String PATH_SHOW_QRCODE = "cgi-bin/showqrcode";

    private static final String PARAMS_TICKET = "ticket";

    public static final String EVENT_SCAN_VALUE = "SCAN";

    public static final String EVENT_SUBSCRIBE_VALUE = "subscribe";

    public static final String SUBSCRIBE_PREFIX = "qrscene_";

    public static final String GET_USER_OPEN_ID = "sns/oauth2/access_token";

    private static final String PATH_JS_API_TICKET = "cgi-bin/ticket/getticket";

    private static final String PARAMS_TYPE = "type";

    private static final String VALUE_TYPE = "jsapi";

    private static final String PARAMS_JS_TICKET = "ticket";
    /**
     * 发送客服消息
     * http://mp.weixin.qq.com/wiki/index.php?title=发送客服消息
     */
    private static final String PATH_MESSAGE_CUSTOM_SEND = "cgi-bin/message/custom/send";
    /**
     * 获取用户基本信息
     * http://mp.weixin.qq.com/wiki/index.php?title=获取用户基本信息
     */
    private static final String PATH_USER_INFO = "cgi-bin/user/info";

    private static final String PARAMS_GRANT_TYPE = "grant_type";

    private static final String CLIENT_CREDENTIAL = "client_credential";

    private static final String PARAMS_APPID = "appid";

//    private static final String VALUE_APPID = "wxc4dbd4dd2ed6dea2";

    private static final String PARAMS_SECRET = "secret";

    private static final String PARAMS_CODE = "code";

    private static final String VALUE_GRANT_TYPE = "authorization_code";

//    private static final String VALUE_SECRET = "983ffc308dac3ccdd4044e0160f65e02";

    /**
     * 参数 - access
     */
    private static final String PARAMS_ACCESS_TOKEN = "access_token";
    /**
     * 参数 - 返回 - 错误消息提示
     */
    private static final String PARAMS_ACCESS_ERRMSG = "errmsg";
    /**
     * 参数 - 微信openid
     */
    private static final String PARAMS_OPENID = "openid";

    /**
     * 结果码 - invalid code(code)
     */
    public static final Integer RESULT_40029 = 40029;

    //    private static final String VALUE_MENU = "{\n" +
//            "    \"button\":[\n" +
//            "    {\n" +
//            "        \"name\":\"预诊\",\n" +
//            "        \"sub_button\":[\n" +
//            "            {\n" +
//            "                \"type\":\"click\",\n" +
//            "                \"name\":\"开始预诊\",\n" +
//            "                \"key\":\"YZ1\"\n" +
//            "            },\n" +
//            "            {\n" +
//            "                \"type\":\"click\",\n" +
//            "                \"name\":\"提交预诊\",\n" +
//            "                \"key\":\"YZ2\"\n" +
//            "            },\n" +
//            "            {\n" +
//            "                \"type\":\"click\",\n" +
//            "                \"name\":\"结束预诊\",\n" +
//            "                \"key\":\"YZ4\"\n" +
//            "            }]\n" +
//            "    },\n" +
//            "    {\n" +
//            "        \"type\":\"click\",\n" +
//            "        \"name\":\"订单查询\",\n" +
//            "        \"key\":\"V1001_TODAY_SINGER\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "        \"name\":\"更多\",\n" +
//            "        \"sub_button\":[\n" +
//            "            {\n" +
//            "                \"type\":\"view\",\n" +
//            "                \"name\":\"搜索\",\n" +
//            "                \"url\":\"http://www.soso.com/\"\n" +
//            "            },\n" +
//            "            {\n" +
//            "                \"type\":\"view\",\n" +
//            "                \"name\":\"视频\",\n" +
//            "                \"url\":\"http://v.qq.com/\"\n" +
//            "            },\n" +
//            "            {\n" +
//            "                \"type\":\"click\",\n" +
//            "                \"name\":\"赞一下我们\",\n" +
//            "                \"key\":\"V1001_GOOD\"\n" +
//            "            }]\n" +
//            "    }]\n" +
//            "}";
    private static final String VALUE_MENU = "{\n" +
            "    \"button\":[\n" +
            "    ]\n" +
            "}";
//    private static final String VALUE_MENU = "{\n" +
//            "}";

    private static final String VALUE_QRCODE_DATA = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";

    //生成临时二维码
    private static final String VALUE_EXPIRE_QRCODE_DATA = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
    /**
     * URL - 音频下载
     */
    private static final String MEDIA_DOWNLOAD_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
    /**
     * URL - 音频上传
     */
    private static final String MEDIA_UPLOAD_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";

    public static final String CALLBACK_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=123&#wechat_redirect";

    /**
     * 扩展XStream，使其支持CDATA块
     */
    private static XStream X_STREAM = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                private boolean cdata = true;

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        if (!StringUtils.isEmpty(text)) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        }
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    static {
        X_STREAM.processAnnotations(WXVoice.class);
        X_STREAM.processAnnotations(WXVoiceMessage.class);
        X_STREAM.processAnnotations(WXBaseMessage.class);
        X_STREAM.processAnnotations(WXImage.class);
        X_STREAM.processAnnotations(WXImageMessage.class);
        X_STREAM.processAnnotations(WXItem.class);
        X_STREAM.processAnnotations(WXNewsMessage.class);
        X_STREAM.processAnnotations(WXTextMessage.class);
    }


    /**
     * 解析微信消息
     *
     * @param request
     * @return
     */
    public WXMessageLog parseMessage(HttpServletRequest request) {
        try (InputStream inputStream = request.getInputStream()) {
            //读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            //noinspection unchecked
            List<Element> elementList = root.elements();
            // 遍历所有子节点
            WXMessageLog log = new WXMessageLog();
            log.setFromType(WXMessageLog.FROM_TYPE_SEND);
//            X_STREAM.fromXML(xml, object); // TODO 未来这里可以改成这个试试

            for (Element e : elementList) {
                if ("MsgType".equals(e.getName())) {
                    log.setMsgType(e.getText());
                    break;
                }
            }
            for (Element e : elementList) {
                switch (e.getName()) {
                    // id
                    case "MsgId":
                        log.setMsgId(e.getText());
                        break;
                    // uid
                    case "FromUserName":
                        log.setUserName(e.getText());
                        break;
                    case "ToUserName":
                        break;
                    case "Recognition":
                        if (log.getVoice() == null) {
                            log.setVoice(new WXVoice(null, null, e.getText()));
                        } else {
                            log.getVoice().setRecognition(e.getText());
                        }
                        break;
                    case "MsgType":
                        break;
                    case "CreateTime":
                        log.setCreateTime(Long.valueOf(e.getText()) * DateUtil.SECOND_LONG);
                        break;
                    case "Content":
                        log.setText(new WXText(e.getText()));
                        break;
                    case "PicUrl":
                        if (log.getImage() == null) {
                            log.setImage(new WXImage(null, e.getText()));
                        } else {
                            log.getImage().setPicUrl(e.getText());
                        }
                        break;
                    case "Format":
                        if (log.getVoice() == null) {
                            log.setVoice(new WXVoice(null, e.getText(), null));
                        } else {
                            log.getVoice().setFormat(e.getText());
                        }
                        break;
                    case "ThumbMediaId":
                        if (log.getVideo() == null) {
                            log.setVideo(new WXVideo(null, e.getText()));
                        } else {
                            log.getVideo().setThumbMediaId(e.getText());
                        }
                        break;
                    case "Location_X":
                        if (log.getLocation() == null) {
                            log.setLocation(new WXLocation(e.getText(), null, null, null));
                        } else {
                            log.getLocation().setLocation_X(e.getText());
                        }
                        break;
                    case "Location_Y":
                        if (log.getLocation() == null) {
                            log.setLocation(new WXLocation(null, e.getText(), null, null));
                        } else {
                            log.getLocation().setLocation_Y(e.getText());
                        }
                        break;
                    case "Scale":
                        if (log.getLocation() == null) {
                            log.setLocation(new WXLocation(null, null, e.getText(), null));
                        } else {
                            log.getLocation().setScale(e.getText());
                        }
                        break;
                    case "Label":
                        if (log.getLocation() == null) {
                            log.setLocation(new WXLocation(null, null, null, e.getText()));
                        } else {
                            log.getLocation().setLabel(e.getText());
                        }
                        break;
                    case "Title":
                        if (log.getLink() == null) {
                            log.setLink(new WXLink(e.getText(), null, null));
                        } else {
                            log.getLink().setTitle(e.getText());
                        }
                        break;
                    case "Description":
                        if (log.getLink() == null) {
                            log.setLink(new WXLink(null, e.getText(), null));
                        } else {
                            log.getLink().setDescription(e.getText());
                        }
                        break;
                    case "Url":
                        if (log.getLink() == null) {
                            log.setLink(new WXLink(null, null, e.getText()));
                        } else {
                            log.getLink().setUrl(e.getText());
                        }
                        break;
                    case "Event":
                        if (log.getEvent() == null) {
                            log.setEvent(new WXEvent(e.getText(), null, null));
                        } else {
                            log.getEvent().setEvent(e.getText());
                        }
                        break;
                    case "EventKey":
                        if (log.getEvent() == null) {
                            log.setEvent(new WXEvent(null, e.getText(), null));
                        } else {
                            log.getEvent().setEventKey(e.getText());
                        }
                        break;
                    case "Ticket":
                        if (log.getEvent() == null) {
                            log.setEvent(new WXEvent(null, null, e.getText()));
                        } else {
                            log.getEvent().setTicket(e.getText());
                        }
                        break;
                    case "MediaId":
                        if (log.getMsgType().equals(MESSAGE_TYPE_IMAGE)) {
                            if (log.getImage() == null) {
                                log.setImage(new WXImage(e.getText(), null));
                            } else {
                                log.getImage().setMediaId(e.getText());
                            }
                        } else if (log.getMsgType().equals(MESSAGE_TYPE_VOICE)) {
                            if (log.getVoice() == null) {
                                log.setVoice(new WXVoice(e.getText(), null, null));
                            } else {
                                log.getVoice().setMediaId(e.getText());
                            }
                        } else if (log.getMsgType().equals(MESSAGE_TYPE_VIDEO)) {
                            if (log.getVideo() == null) {
                                log.setVideo(new WXVideo(e.getText(), null));
                            } else {
                                log.getVideo().setMediaId(e.getText());
                            }
                        } else if (log.getMsgType().equals(MESSAGE_TYPE_VOICE)) {
                            if (log.getVoice() == null) {
                                log.setVoice(new WXVoice(e.getText(), null, null));
                            } else {
                                log.getVoice().setMediaId(e.getText());
                            }
                        }
                        break;
                    case "Encrypt":
                        log.setEncrypt(e.getText());
                        break;
                    // TODO 图文的解析没有！
                    default: // 未知属性，可能是微信增加了新的元素等等原因，日志做下记录！
                        LOGGER.error("[parseMessage][document({}) 未知({}={})]", document.getText(),
                                e.getName(), e.getText());
                        break;
                }
            }

            if (log.getMsgType().equals(MESSAGE_TYPE_EVENT)) {//事件无消息msgId 手动生成
                log.setMsgId(log.getUserName() + log.getCreateTime());//官网关于重试的消息排重，推荐使用FromUserName + CreateTime 排重。
            }

            return log;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 文本消息对象转换成xml
     *
     * @param obj 文本消息对象
     * @return xml
     */
    public String textMessageToXml(Object obj) {
        return X_STREAM.toXML(obj);
    }

    /**
     * 发送客服消息
     * TODO 妈蛋，写了半天代码发现订阅号不能发送客服消息！！！操
     * messageCustomSend:::::::::::::{"errcode":48001,"errmsg":"api unauthorized hint: [6Ao5_0310vr45!]"}
     *
     * @param obj
     */
    public void messageCustomSend(WXBaseMessage obj) {
        List<String> messages = new ArrayList<>(0);
        for (int i = 0; i < HTTP_RETRY_COUNT; i++) {
            String accessToken = getAccessToken();
            CloseableHttpResponse response = null;
            try {
                URIBuilder builder = new URIBuilder()
                        .setScheme(SCHEME)
                        .setHost(HOST)
                        .setPath(PATH_MESSAGE_CUSTOM_SEND)
                        .addParameter(PARAMS_ACCESS_TOKEN, accessToken);
                StringEntity reqEntity = new StringEntity(JsonUtil.toString(obj), ContentType.APPLICATION_JSON);
                HttpPost post = new HttpPost(builder.build());
                post.setEntity(reqEntity);
                response = HttpUtil.getHttpClient().execute(post);
                String responseText = EntityUtils.toString(response.getEntity(), Consts.UTF_8);

                LOGGER.info("messageCustomSend:::::::::::::" + responseText);

            } catch (Exception e) {
                LOGGER.error("", e);
            } finally {
                HttpUtil.closeQuietly(response);
            }
        }
        // TODO 这里未来要改个写法。因为如果对方服务有问题，不要去sleep，需要改成delay message，延迟消费。
        if (JsonUtil.toString(messages).contains("system error")) {
            try {
                LOGGER.info("[messageCustomSend][message({}) system error, sleep 5 second and retry]", JsonUtil.toString(obj));
                Thread.sleep(5000);
                messageCustomSend(obj);//出现system error错误后等待5秒后重试
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException(String.format("[messageCustomSend] message(%s) exceptions(%s)", JsonUtil.toString(obj),
                JsonUtil.toString(messages)));
    }

    public String messageResponse(WXMessageLog wxMsg) {

//         <xml>
//        <ToUserName><![CDATA[粉丝号]]></ToUserName>
//        <FromUserName><![CDATA[公众号]]></FromUserName>
//        <CreateTime>1460541339</CreateTime>
//        <MsgType><![CDATA[text]]></MsgType>
//        <Content><![CDATA[test]]></Content>
//        </xml>

        WXTextMessage wxTextMessage = new WXTextMessage();
        wxTextMessage.setText(new WXText("测试"));
        wxTextMessage.setCreateTime(System.currentTimeMillis());
        wxTextMessage.setFromUserName("gh_60534a726e98");
        wxTextMessage.setToUserName(wxMsg.getUserName());
        return textMessageToXml(wxTextMessage);
    }

    private String mediaUploadSuffix(String type) {
        switch (type) {
            case MEDIA_TYPE_IMAGE:
                return ".jpg";
            case MEDIA_TYPE_VOICE:
                return ".amr";
            case MEDIA_TYPE_VIDEO:
                return ".mp4";
            case MEDIA_TYPE_THUMB:
                return ".jpg";
            default:
                throw new RuntimeException(String.format("type(%s) not supported", type));
        }
    }

    /**
     * 获取accessToken
     *
     * @return
     */
    public String getHttpAccessToken() {
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder()
                    .setScheme(SCHEME)
                    .setHost(HOST)
                    .setPath(PATH_ACCESS_TOKEN)
                    .addParameter(PARAMS_GRANT_TYPE, CLIENT_CREDENTIAL)
                    .addParameter(PARAMS_APPID, AppContext.getProperty("weixin.appID"))
                    .addParameter(PARAMS_SECRET, AppContext.getProperty("weixin.appSecret"));
            HttpGet get = new HttpGet(builder.build());
            response = HttpUtil.getHttpClient().execute(get);
            String responseText = EntityUtils.toString(response.getEntity());
            LOGGER.info(responseText);
            return (String) JsonUtil.parse(responseText, Map.class).get("access_token");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void refreshAccessToken() {
        String accessToken = getHttpAccessToken();
        LOGGER.info("refreshAccessToken: " + accessToken);
        setAccessToken(accessToken);
    }

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
