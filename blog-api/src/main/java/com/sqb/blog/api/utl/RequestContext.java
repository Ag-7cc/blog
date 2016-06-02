package com.sqb.blog.api.utl;

import java.util.HashMap;
import java.util.Map;

import com.sqb.blog.util.exception.BizException;
import com.sqb.blog.util.respcode.RespCode;

/**
 * 请求上下文
 * 
 * @author elvis.xu
 * @since 2014-12-1
 */
public class RequestContext {

	private static final String KEY_CLIENT_IP = RequestContext.class.getName() + ".client.ip";
	private static final String KEY_CLIENT_USER_ID = RequestContext.class.getName() + ".client.user.id";
	private static final String KEY_REQ_URI = RequestContext.class.getName() + ".req.uri";
	private static final String KEY_REQ_METHOD = RequestContext.class.getName() + ".req.method";

	protected static ThreadLocal<Map<String, Object>> requestThreadLocal = new ThreadLocal<Map<String, Object>>();

	private RequestContext() {}

	public static Map<String, Object> get() {
		Map<String, Object> map = requestThreadLocal.get();
		if (map == null) {
			map = new HashMap<String, Object>();
			requestThreadLocal.set(map);
		}
		return map;
	}

	public static void put(String key, Object val) {
		get().put(key, val);
	}

	public static Object get(String key) {
		return get().get(key);
	}

	public static String getAsString(String key) {
		return (String) get().get(key);
	}

	public static void putUserId(Integer userId) {
		put(KEY_CLIENT_USER_ID, userId);
	}

	public static Integer getUserId() {
		return (Integer) get(KEY_CLIENT_USER_ID);
	}

	public static Integer checkAndGetUserId() {
		Integer userId = getUserId();
		if (userId == null) {
			throw new BizException(RespCode.RES_0100);
		}
		return userId;
	}

	public static void putClientIp(String clientIp) {
		put(KEY_CLIENT_IP, clientIp);
	}

	public static String getClientIp() {
		return getAsString(KEY_CLIENT_IP);
	}

	public static void putReqUri(String uri) {
		put(KEY_REQ_URI, uri);
	}

	public static String getReqUri() {
		return getAsString(KEY_REQ_URI);
	}

	public static void putReqMethod(String method) {
		put(KEY_REQ_METHOD, method);
	}

	public static String getReqMethod() {
		return getAsString(KEY_REQ_METHOD);
	}

	public static void clear() {
		requestThreadLocal.remove();
	}

}
