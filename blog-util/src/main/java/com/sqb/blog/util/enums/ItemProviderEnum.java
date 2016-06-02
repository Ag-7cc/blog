package com.sqb.blog.util.enums;

/**
 * 商品提供商
 * 
 * @author elvis.xu
 */
public enum ItemProviderEnum {

	SELF((byte) 1, "自营"), 
	HYS((byte) 2, "好药师");

	byte code;
	String desc;

	ItemProviderEnum(byte code, String desc) {
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
		return ItemProviderEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static ItemProviderEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (ItemProviderEnum e : ItemProviderEnum.values()) {
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
