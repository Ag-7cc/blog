package com.sqb.blog.util.enums;

public enum SysConfigEnum {
	
	INDEX_BANNERS("INDEX_BANNERS", "首页内容配置"),
	EXPRESS_FEE("EXPRESS_FEE", "运费配置");

	private String code;
	private String name;

	private SysConfigEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
