package com.cbp.ticketing.model;

public class ResponseBodyHelper {
	private String statusCode;
	private String reqStatus;
	private String message;
	private Object result;

	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(Object result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "ResponseBodyHelper [statusMsg=" + reqStatus + ", message=" + message
				+ ", result=" + result + "]";
	}


}
