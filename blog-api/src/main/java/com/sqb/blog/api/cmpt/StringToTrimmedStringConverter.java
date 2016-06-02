package com.sqb.blog.api.cmpt;

import org.springframework.core.convert.converter.Converter;

/**
 * Trim输入的参数
 */
public class StringToTrimmedStringConverter implements Converter<String, String> {

	@Override
	public String convert(String source) {
		return source == null ? null : source.trim();
	}

}
