package com.sqb.blog.util.enums;

/**
 * 操作日志目标类型
 * 
 * @author elvis.xu
 */
public enum OperLogTargetTypeEnum {

	ORDER((byte) 1, "订单"), 
	PAY((byte) 2, "支付"),
	REFUND((byte) 3, "退款");

	byte code;
	String desc;

	OperLogTargetTypeEnum(byte code, String desc) {
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
		return OperLogTargetTypeEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static OperLogTargetTypeEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (OperLogTargetTypeEnum e : OperLogTargetTypeEnum.values()) {
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
