package com.sqb.blog.util.enums;

/**
 * 好药师商品状态
 * 
 * @author elvis.xu
 */
public enum ItemHysStatusEnum {

	SHELVE((byte) 0, "上架"), 
	UNSHELVE((byte) 1, "下架");

	byte code;
	String desc;

	ItemHysStatusEnum(byte code, String desc) {
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
		return ItemHysStatusEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static ItemHysStatusEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (ItemHysStatusEnum e : ItemHysStatusEnum.values()) {
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
