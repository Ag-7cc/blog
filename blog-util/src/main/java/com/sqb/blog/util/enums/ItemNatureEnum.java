package com.sqb.blog.util.enums;

/**
 * 商品固性
 * 
 * @author elvis.xu
 */
public enum ItemNatureEnum {

	REAL_GOODS((byte) 1, "实物"), 
	VIRTUAL_GOODS((byte) 2, "虚拟");

	byte code;
	String desc;

	ItemNatureEnum(byte code, String desc) {
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
		return ItemNatureEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static ItemNatureEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (ItemNatureEnum e : ItemNatureEnum.values()) {
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
