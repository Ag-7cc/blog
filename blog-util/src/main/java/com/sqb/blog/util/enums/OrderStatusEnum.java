package com.sqb.blog.util.enums;

/**
 * 订单状态
 * 
 * @author elvis.xu
 */
public enum OrderStatusEnum {

	WAITING_PAY((byte) 0, "待支付"), /*用户下订单得来-可支付*/
	CANCELLED((byte) 1, "已取消"), /*用户取消订单得来-无*/
	EXPIRED((byte) 2, "已过期"), /*跑批或订单判断时得来-无*/ 
	PAYING((byte) 11, "支付中"), /*暂时不用*/
	PAID((byte) 12, "已支付"), /*好药师支付回调得来-等待好药师物流回调*/
	PAY_FAIL((byte) 13, "支付失败"), /*好药师支付回调得来-可再支付*/
	// PAID_BUT_DEDUCT_FAIL((byte) 14, "已支付但抵扣失败"), /*好药师支付回调抵扣失败得来-须人工处理*/
	DELIVERING((byte) 21, "发货中"), /*好药师物流回调得来-无*/
	DELIVERED((byte) 22, "发货成功"), /*暂时不用*/
	DELIVER_FAIL((byte) 23, "发货失败"), /*暂时不用*/
	WAITING_REFUND((byte) 30, "待退款"), /*用户申请退款得来-人工处理*/
	REFUNDING((byte) 31, "退款中"), /*暂时不用*/
	REFUNDED((byte) 32, "已退款"), /*人工状态修改得来-无*/
	REFUND_FAIL((byte) 33, "退款失败"), /*暂时不用*/
	UNKNOWN((byte) 99, "订单异常"), /*暂时不用*/
	;

	byte code;
	String desc;

	OrderStatusEnum(byte code, String desc) {
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
		return OrderStatusEnum.class.getSimpleName() + "[code=" + code + ", desc=" + desc + "]";
	}

	public static OrderStatusEnum valueOfCode(Byte code) {
		if (code == null) {
			return null;
		}
		for (OrderStatusEnum e : OrderStatusEnum.values()) {
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
