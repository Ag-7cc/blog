package com.sqb.blog.util.enums;

/**
 * 商品状态
 * 
 * @author elvis.xu
 */
public enum ItemStatusEnum {

	NORMAL((byte) 0, "正常"), 
	UNSHELVE((byte) 1, "下架"), 
	DELETE((byte) 2, "删除");

	byte code;
	String desc;

	ItemStatusEnum(byte code, String desc) {
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
		return ItemStatusEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static ItemStatusEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (ItemStatusEnum e : ItemStatusEnum.values()) {
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
