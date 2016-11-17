package com.sqb.blog.util;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Http工具类
 * Created by vic.shan
 * Date: 2016/11/17.14:46
 */
public class HttpUtil {

    /**
     * 内容类型 - UTF-8
     */
    public static final ContentType TEXT_PLAIN_UTF_8 = ContentType.create("text/plain", Consts.UTF_8);
    /**
     * 一些爬虫的UA
     */
    public static final Set<String> SPIDER_UAS = ImmutableSet.of("spider", "Jakarta");

    /**
     * HttpClient单例持有
     */
    private static class HttpClientHolder {
        private static final CloseableHttpClient INSTANCE = HttpClients.custom()
                .disableAutomaticRetries()
                .setMaxConnTotal(10240)
                .setMaxConnPerRoute(512)
                .setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout((int) (30 * DateUtil.SECOND_LONG)).setSocketTimeout((int) (30 * DateUtil.SECOND_LONG)).build())// TODO 【要设置下httpclient的默认超时时间】
                .setUserAgent("")
                .build();
    }

    /**
     * 获取Cookie方法
     *
     * @param request request对象
     * @param name    Cookie名称
     * @return 值
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 添加Cookie方法
     *
     * @param response response对象
     * @param name     Cookie名称
     * @param value    值
     */
    public static void addCookie(HttpServletResponse response, String name, String value) {
        addCookie(response, name, value, (int) DateUtil.YEAR_LONG);
    }

    /**
     * 添加Cookie方法
     *
     * @param response response对象
     * @param name     Cookie名称
     * @param value    值
     * @param maxAge   最长存活时间（秒）
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        addCookie(response, name, value, maxAge, "/");
    }

    /**
     * 添加Cookie方法
     *
     * @param response response对象
     * @param name     Cookie名称
     * @param value    值
     * @param maxAge   最长存活时间（秒）
     * @param path     存放路径
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * 移除Cookie方法
     *
     * @param response response对象
     * @param name     Cookie名称
     */
    public static void removeCookie(HttpServletResponse response, String name) {
        addCookie(response, name, null, 0);
    }

    /**
     * @return HttpClient单例
     */
    public static CloseableHttpClient getHttpClient() {
        return HttpClientHolder.INSTANCE;
    }

    /**
     * @param request request对象
     * @return ip
     */
    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("clientip"); // for UC browser
        if (ip == null) {
            ip = request.getHeader("X-Real-IP");
            if (ip == null) {
                ip = request.getHeader("x-forwarded-for");
                if (ip == null) {
                    ip = request.getRemoteAddr();
                }
            }
        }
        return ip;
    }

    /**
     * 安静的关闭，即使抛出异常
     *
     * @param response 响应
     */
    public static void closeQuietly(CloseableHttpResponse response) {
        if (response == null) {
            return;
        }
        try {
            response.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * @param request request对象
     * @return ip转化为int
     */
    public static long getIPNum(HttpServletRequest request) {
        String[] ips = getIP(request).split("\\.");
        return (Long.valueOf(ips[0]) << 24)
                + (Integer.valueOf(ips[1]) << 16)
                + (Integer.valueOf(ips[2]) << 8)
                + (Integer.valueOf(ips[3]));
    }

    /**
     * @param request 请求
     * @return ua
     */
    public static String getUA(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        return ua != null ? ua : "";
    }

    public static String getUA(ServletRequest request) {
        return getUA((HttpServletRequest) request);
    }

    /**
     * @param request 请求
     * @return ua
     */
    public static String getReferer(HttpServletRequest request) {
        return request.getHeader("Referer");
    }

    /**
     * 根据map建立queryString。主要用于生成Http的Get请求的参数
     *
     * @param params 参数集
     * @return queryString
     */
    public static String buildQueryString(Map<String, Object> params) {
        if (params.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    /**
     * url参数编码，按照UTF-8来
     *
     * @param value 参数
     * @return 编码后的参数
     */
    public static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, AppContext.CHARSET.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * url参数解码，按照UTF-8来
     *
     * @param value 参数
     * @return 解码后的参数
     */
    public static String urlDecode(String value) {
        try {
            return URLDecoder.decode(value, AppContext.CHARSET.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据request拼接queryString
     *
     * @return queryString
     */
    public static String buildQueryString(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        Enumeration<String> es = request.getParameterNames();
        if (!es.hasMoreElements()) {
            return "";
        }
        String parameterName, parameterValue;
        StringBuilder params = new StringBuilder();
        while (es.hasMoreElements()) {
            parameterName = es.nextElement();
            parameterValue = request.getParameter(parameterName);
            params.append(parameterName).append("=").append(parameterValue).append("&");
        }
        return params.deleteCharAt(params.length() - 1).toString();
    }

    public static byte[] getImageBytes(String url) {
//        try (InputStream is = new URL(url).openConnection().getInputStream()) {
//            return IOUtils.toByteArray(is);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        CloseableHttpResponse response = null;
        try {
            HttpGet get = new HttpGet(url);
            response = HttpUtil.getHttpClient().execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return EntityUtils.toByteArray(response.getEntity());
            } else {
                throw new IOException("返回结果：" + statusCode);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    /**
     * TODO WeixinCenterClient
     * 从微信获得语音文件
     *
     * @param url
     * @return
     */
    public static byte[] getMediaFromWX(String url) {
        CloseableHttpResponse response = null;
        try {
            HttpGet get = new HttpGet(url);
            response = HttpUtil.getHttpClient().execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("读取微信语音结果：" + response.getEntity().toString());
                return EntityUtils.toByteArray(response.getEntity());
            } else {
                throw new IOException("返回结果：" + statusCode);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    public static List<NameValuePair> getParams(Map<String, ?> params) {
        List<NameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, ?> param : params.entrySet()) {
            if (param.getValue() == null) {
                continue;
            }
            nvps.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }
        return nvps;
    }

    public static String request(String uri, String method, Map<String, Object> params, Map<String, String> headers,
                                 RequestConfig config, HttpClientContext context, String charset) {
        HttpRequestBase req;
        CloseableHttpResponse response = null;
        try {
            if (method.equalsIgnoreCase("post")) {
                req = new HttpPost(uri);
                HttpPost post = (HttpPost) req;
                List<NameValuePair> nvps = getParams(params);
                post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            } else if (method.equalsIgnoreCase("get")) {
                if (params.isEmpty()) {
                    req = new HttpGet(uri);
                } else {
                    URIBuilder builder = new URIBuilder();
                    builder.setPath(uri);
                    for (Map.Entry<String, Object> entry : params.entrySet()) {
                        builder.addParameter(entry.getKey(), String.valueOf(entry.getValue()));
                    }
                    req = new HttpGet(builder.toString());
                }
//                HttpGet get = new HttpGet();
//                get.set
            } else if (method.equals("post_with_file")) {
                req = new HttpPost(uri);
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    if (entry.getValue() instanceof Object[]) {
                        Object[] values = (Object[]) entry.getValue();
                        if ("binary".equals(values[0])) {
                            builder.addBinaryBody(entry.getKey(), (byte[]) values[1], ContentType.APPLICATION_OCTET_STREAM, (String) values[2]);
                        }
                    } else {
                        builder.addTextBody(entry.getKey(), String.valueOf(entry.getValue()), HttpUtil.TEXT_PLAIN_UTF_8);
                    }
                }
                ((HttpPost) req).setEntity(builder.build());
            } else if (method.equals("post_with_json")) {
                req = new HttpPost(uri);
                HttpPost post = (HttpPost) req;
                post.setEntity(new StringEntity(JsonUtil.toString(params), ContentType.APPLICATION_JSON));
            } else if (method.equals("get_with_json")) {
                req = new HttpPost(uri);
                HttpPost post = (HttpPost) req;
//                post.
                post.setEntity(new StringEntity(JsonUtil.toString(params), ContentType.APPLICATION_JSON));
            } else if (method.equals("delete")) {
                URIBuilder builder = new URIBuilder();
                builder.setPath(uri);
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    builder.addParameter(entry.getKey(), String.valueOf(entry.getValue()));
                }
                req = new HttpDelete(builder.toString());
            } else {
                throw new IllegalArgumentException(method);
            }

            if (null != headers && headers.size() > 0) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    req.addHeader(header.getKey(), header.getValue());
                }
            }

            if (config != null) {
                req.setConfig(config);
            }

            if (context == null) {
                context = HttpClientContext.create();
                context.setCookieStore(new BasicCookieStore()); // 防止其他请求的cookie影响
            }
            Integer count = 1;
            while (true) {
                try {
                    response = HttpUtil.getHttpClient().execute(req, context);
                    return EntityUtils.toString(response.getEntity(), charset);
                } catch (SocketException e) {
                    if (count > 3) {
                        throw new RuntimeException(e);
                    }
                    count++;
                    Thread.sleep(5000);
                } catch (UnknownHostException e) {
                    if (count > 3) {
                        throw new RuntimeException(e);
                    }
                    count++;
                    Thread.sleep(5000);
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            HttpUtil.closeQuietly(response);
        }
    }

    public static String request(String uri, String method, Map<String, Object> params, Map<String, String> headers,
                                 RequestConfig config, HttpClientContext context) {
        return request(uri, method, params, headers, config, context, "utf-8");
    }

    public static String request(String uri, String method, Map<String, Object> params, Map<String, String> headers,
                                 RequestConfig config) {
        return request(uri, method, params, headers, config, null);
    }

    /**
     * 获得Int类型的参数。首先从request参数中拿，若不存在，则从Cookie中获得。
     *
     * @param request 请求
     * @param key     参数名
     * @return 参数值
     */
    public static Integer getIntParameter(HttpServletRequest request, String key) {
        String value = getParameter(request, key);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return Integer.valueOf(value);
    }

    /**
     * 获得String类型的参数。首先从request参数中拿，若不存在，则从Cookie中获得。
     *
     * @param request 请求
     * @param key     参数名
     * @return 参数值
     */
    public static String getParameter(HttpServletRequest request, String key) {
        String value = request.getParameter(key);
       /* if (value == null && AppContext.DEV) {
            value = HttpUtil.getCookie(request, genCookieKey(key));
        }*/
        return value;
    }

    /**
     * 从Cookie中获得。
     *
     * @param request 请求
     * @param key     参数名
     * @return 参数值
     */
    public static String getParameterFromCookie(HttpServletRequest request, String key) {
        return HttpUtil.getCookie(request, genCookieKey(key));
    }

    /**
     * @param key 键
     * @return 新键。防止和其他应用cookie冲突
     */
    public static String genCookieKey(String key) {
        return "apps_server_" + key;
    }

    /**
     * 判断 request 是否包含 爬虫的ua
     *
     * @param request request
     * @return true:包含  false:不包含
     */
    public static boolean containsSpiderUA(HttpServletRequest request) {
        String ua = getUA(request);
        for (String patten : SPIDER_UAS) {
            if (Pattern.compile(".*" + patten + "*.", Pattern.CASE_INSENSITIVE).matcher(ua).matches()) {
                return true;
            }
        }
        return false;
    }
}
