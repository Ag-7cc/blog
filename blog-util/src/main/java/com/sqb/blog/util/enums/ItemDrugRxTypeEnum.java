package com.sqb.blog.util.enums;

/**
 * 药品处方类型
 * 
 * @author elvis.xu
 */
public enum ItemDrugRxTypeEnum {

	OTC((byte) 1, "非处方药"), 
	RX_MONORAIL((byte) 2, "单轨处方药"), 
	RX_DOUBLE_TRACK((byte) 3, "双轨处方药");

	byte code;
	String desc;

	ItemDrugRxTypeEnum(byte code, String desc) {
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
		return ItemDrugRxTypeEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static ItemDrugRxTypeEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (ItemDrugRxTypeEnum e : ItemDrugRxTypeEnum.values()) {
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
