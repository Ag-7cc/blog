package com.sqb.blog.util.enums;

/**
 * 商品活动状态
 * 
 * @author elvis.xu
 */
public enum ItemActivityStatusEnum {

	NORMAL((byte) 0, "正常"), 
	INVALID((byte) 1, "无效");

	byte code;
	String desc;

	ItemActivityStatusEnum(byte code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public byte getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public String toString() {
		return ItemActivityStatusEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static ItemActivityStatusEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (ItemActivityStatusEnum e : ItemActivityStatusEnum.values()) {
			if (e.getCode() == code.byteValue()) {
				return e;
			}
		}
		return null;
	}

	public static boolean validCode(String code) {
		Byte val = null;
		try {
			val = Byte.valueOf(code);
		} catch (NumberFormatException e) {
			return false;
		}
		if (valueOfCode(val) == null) {
			return false;
		}
		return true;
	}

}
