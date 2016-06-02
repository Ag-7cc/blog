package com.sqb.blog.util.enums;

/**
 * 退款方式
 * 
 * @author elvis.xu
 */
public enum RefundWayEnum {

	OTHER((byte) 0, "其它"),
	HYS((byte) 1, "好药师");

	byte code;
	String desc;

	RefundWayEnum(byte code, String desc) {
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
		return RefundWayEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static RefundWayEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (RefundWayEnum e : RefundWayEnum.values()) {
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
