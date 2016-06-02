package com.sqb.blog.biz.bo.param;

public class OrderSaveParam {
	private Integer userId;
	private Byte orderType;
	private Long origPrice;
	private Long realPrice;
	private String intro;
	private String receiverName;
	private String receiverMobile;
	private String receiverProvince;
	private String receiverCity;
	private String receiverRegion;
	private String receiverZipcode;
	private String receiverAddress;
	private Byte couponFlag;
	private Long deductCoin;
	private Long expressFee;
	private Long amount;
	private Long itemId;
	private String itemName;
	private Integer itemNum;
	private String providerRefId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Byte getOrderType() {
		return orderType;
	}

	public void setOrderType(Byte orderType) {
		this.orderType = orderType;
	}

	public Long getOrigPrice() {
		return origPrice;
	}

	public void setOrigPrice(Long origPrice) {
		this.origPrice = origPrice;
	}

	public Long getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(Long realPrice) {
		this.realPrice = realPrice;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverProvince() {
		return receiverProvince;
	}

	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverRegion() {
		return receiverRegion;
	}

	public void setReceiverRegion(String receiverRegion) {
		this.receiverRegion = receiverRegion;
	}

	public String getReceiverZipcode() {
		return receiverZipcode;
	}

	public void setReceiverZipcode(String receiverZipcode) {
		this.receiverZipcode = receiverZipcode;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public Byte getCouponFlag() {
		return couponFlag;
	}

	public void setCouponFlag(Byte couponFlag) {
		this.couponFlag = couponFlag;
	}

	public Long getDeductCoin() {
		return deductCoin;
	}

	public void setDeductCoin(Long deductCoin) {
		this.deductCoin = deductCoin;
	}

	public Long getExpressFee() {
		return expressFee;
	}

	public void setExpressFee(Long expressFee) {
		this.expressFee = expressFee;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public String getProviderRefId() {
		return providerRefId;
	}

	public void setProviderRefId(String providerRefId) {
		this.providerRefId = providerRefId;
	}

}
