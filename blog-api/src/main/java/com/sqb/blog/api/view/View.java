package com.sqb.blog.api.view;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.sqb.blog.api.view.ViewBody;

@JsonAutoDetect
public class View {
	private String returnCode;
	private String returnMsg;
	private long timestamp;

	@JsonIgnore
	private String originalCode;
	@JsonIgnore
	private String originalMsg;

	@JsonUnwrapped
	private ViewBody body;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String resultCode) {
		this.returnCode = resultCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String resultMsg) {
		this.returnMsg = resultMsg;
	}

	public ViewBody getBody() {
		return body;
	}

	public View setBody(ViewBody body) {
		this.body = body;
		return this;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getOriginalCode() {
		return originalCode;
	}

	public void setOriginalCode(String originalCode) {
		this.originalCode = originalCode;
	}

	public String getOriginalMsg() {
		return originalMsg;
	}

	public void setOriginalMsg(String originalMsg) {
		this.originalMsg = originalMsg;
	}

	@Override
	public String toString() {
		return "View [returnCode=" + returnCode + ", returnMsg=" + returnMsg + ", timestamp=" + timestamp + ", originalCode="
				+ originalCode + ", originalMsg=" + originalMsg + ", body=" + body + "]";
	}

}
