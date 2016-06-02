package com.sqb.blog.util.enums;

/**
 * 支付方式
 * 
 * @author elvis.xu
 */
public enum PayWayEnum {

	HYS((byte) 1, "好药师");

	byte code;
	String desc;

	PayWayEnum(byte code, String desc) {
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
		return PayWayEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static PayWayEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (PayWayEnum e : PayWayEnum.values()) {
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
