package com.sqb.blog.util;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.Properties;

/**
 * 应用上下文环
 * @author elvis.xu
 * @since 2015-6-4
 */
public class AppContext {

	/**
	 * 系统默认编码
	 */
	public static final Charset CHARSET = Charset.forName("UTF-8");

	protected static AppContext instance;

	protected Properties properties;

	public AppContext(Properties properties) {
		if (instance == null) {
			instance = this;
			this.properties = properties;
		}
	}

	public static String getServerWebSite(){
		if (instance.properties == null) {
			return null;
		}
		return instance.properties.getProperty("server_web_site");
	}

	public static String getProperty(String key) {
		if (instance.properties == null) {
			return null;
		}
		return instance.properties.getProperty(key);
	}

	public static boolean isEnvProduct() {
		return "product".equalsIgnoreCase(getProperty("env"));
	}

	public static boolean isPwdField(String name) {
		name = StringUtils.trimToNull(name);

		if (name != null
				&& (StringUtils.endsWithIgnoreCase(name, "pwd") || StringUtils.endsWithIgnoreCase(name, "password") || StringUtils
						.endsWithIgnoreCase(name, "pass"))) {
			return true;
		}
		return false;
	}

	public static String getPwdMask() {
		return "[******]";
	}
}
