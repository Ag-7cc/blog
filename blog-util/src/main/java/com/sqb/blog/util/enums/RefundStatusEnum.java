package com.sqb.blog.util.enums;

/**
 * 退款状态
 * 
 * @author elvis.xu
 */
public enum RefundStatusEnum {

	WAITING_REFUND((byte) 0, "待退款"),
	REFUNDING((byte) 1, "退款中"),
	REFUNDED((byte) 2, "已退款"),
	REFUND_FAIL((byte) 3, "退款失败")
	;

	byte code;
	String desc;

	RefundStatusEnum(byte code, String desc) {
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
		return RefundStatusEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static RefundStatusEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (RefundStatusEnum e : RefundStatusEnum.values()) {
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
