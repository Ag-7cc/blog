package com.sqb.blog.util.respcode;

/**
 * 返回码
 * @author vic.Shan
 * @since 2016-7-8
 */
public interface RespCode {

	/** 成功 */
	String RES_0000 = "0000";

	/** 参数{0}格式错误: {1} */
	String RES_0010 = "0010";
	/** 参数{0}必须为有效的枚举值 */
	String RES_0011 = "0011";
	/** 参数{0}不能为空 */
	String RES_0012 = "0012";
	/** 参数{0}取值不在范围内 */
	String RES_0013 = "0013";
	/** 查询不到记录: {0}={1} */
	String RES_0014 = "0014";

	/** 请先登录 */
	String RES_0100 = "0100";
	/** TOKEN无效，请重新登录 */
	String RES_0101 = "0101";

	/** 内部错误 */
	String RES_9999 = "9999";

}
