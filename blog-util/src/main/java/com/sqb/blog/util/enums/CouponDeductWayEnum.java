package com.sqb.blog.util.enums;

/**
 * 抵扣方式
 * 
 * @author elvis.xu
 */
public enum CouponDeductWayEnum {

	CASH((byte) 1, "现金抵扣"), 
	DISCOUNT((byte) 2, "折扣");

	byte code;
	String desc;

	CouponDeductWayEnum(byte code, String desc) {
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
		return CouponDeductWayEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static CouponDeductWayEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (CouponDeductWayEnum e : CouponDeductWayEnum.values()) {
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
