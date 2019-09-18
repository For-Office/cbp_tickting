package com.cbp.ticketing.model;

public class TicketResCredentials {
	private int resId;
	private String resKey;
	private String resValue;
	private String is_encrypted;
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public String getResKey() {
		return resKey;
	}
	public void setResKey(String resKey) {
		this.resKey = resKey;
	}
	public String getResValue() {
		return resValue;
	}
	public void setResValue(String resValue) {
		this.resValue = resValue;
	}
	public String getIs_encrypted() {
		return is_encrypted;
	}
	public void setIs_encrypted(String is_encrypted) {
		this.is_encrypted = is_encrypted;
	}

}
