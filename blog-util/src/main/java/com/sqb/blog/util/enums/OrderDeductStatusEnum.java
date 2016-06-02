package com.sqb.blog.util.enums;

/**
 * 订单抵扣状态
 * 
 * @author elvis.xu
 */
public enum OrderDeductStatusEnum {

	INACTIVE((byte) 0, "未生效"),
	ACTIVE((byte) 2, "生效"),
	;

	byte code;
	String desc;

	OrderDeductStatusEnum(byte code, String desc) {
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
		return OrderDeductStatusEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static OrderDeductStatusEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (OrderDeductStatusEnum e : OrderDeductStatusEnum.values()) {
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
