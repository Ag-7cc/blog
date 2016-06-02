package com.sqb.blog.util.enums;

/**
 * 抵扣券状态
 * 
 * @author elvis.xu
 */
public enum CouponStatusEnum {

	NOT_USE((byte) 0, "未使用"), 
	USING((byte) 1, "使用中"), 
	USED((byte) 2, "已使用"), 
	EXPIRED((byte) 3, "已过期");

	byte code;
	String desc;

	CouponStatusEnum(byte code, String desc) {
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
		return CouponStatusEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static CouponStatusEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (CouponStatusEnum e : CouponStatusEnum.values()) {
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
