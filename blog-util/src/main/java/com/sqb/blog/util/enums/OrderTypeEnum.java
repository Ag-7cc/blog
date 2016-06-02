package com.sqb.blog.util.enums;

/**
 * 订单类型
 * 
 * @author elvis.xu
 */
public enum OrderTypeEnum {

	NORMAL((byte) 1, "普通订单"), 
	RX((byte) 2, "处方药订单"), 
	CREDIT((byte) 3, "信用支付订单");

	byte code;
	String desc;

	OrderTypeEnum(byte code, String desc) {
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
		return OrderTypeEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static OrderTypeEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (OrderTypeEnum e : OrderTypeEnum.values()) {
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
