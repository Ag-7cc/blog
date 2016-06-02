package com.sqb.blog.util.enums;

/**
 * 支付状态
 * 
 * @author elvis.xu
 */
public enum PayStatusEnum {

	WAITING_PAY((byte) 0, "待支付"),
	PAYING((byte) 1, "支付中"),
	PAID((byte) 2, "已支付"),
	PAY_FAIL((byte) 3, "支付失败"),
	UNKNOWN((byte) 99, "支付异常")
	;

	byte code;
	String desc;

	PayStatusEnum(byte code, String desc) {
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
		return PayStatusEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static PayStatusEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (PayStatusEnum e : PayStatusEnum.values()) {
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
