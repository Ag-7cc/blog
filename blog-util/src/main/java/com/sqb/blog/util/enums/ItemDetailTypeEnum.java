package com.sqb.blog.util.enums;

/**
 * 商品详情分类
 * 
 * @author elvis.xu
 */
public enum ItemDetailTypeEnum {

	UNKNOWN((byte) 0, "未知"), 
	DRUG((byte) 1, "药品"), 
	INSTRUMENT((byte) 2, "设备");

	byte code;
	String desc;

	ItemDetailTypeEnum(byte code, String desc) {
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
		return ItemDetailTypeEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static ItemDetailTypeEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (ItemDetailTypeEnum e : ItemDetailTypeEnum.values()) {
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
