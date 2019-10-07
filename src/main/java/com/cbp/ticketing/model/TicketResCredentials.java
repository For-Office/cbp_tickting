package com.cbp.ticketing.model;

import java.util.Date;
import java.util.List;

public class TicketResCredentials {
	private int resId;
	private String resKey;
	private String resValue;
	private String isEncrypted;
	private List<String> resTypeKeys;
	private List<String> resTypeValues;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String isDeleted;
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Date getDeletedDate() {
		return deletedDate;
	}
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public List<String> getResTypeKeys() {
		return resTypeKeys;
	}
	public void setResTypeKeys(List<String> resTypeKeys) {
		this.resTypeKeys = resTypeKeys;
	}
	public List<String> getResTypeValues() {
		return resTypeValues;
	}
	public void setResTypeValues(List<String> resTypeValues) {
		this.resTypeValues = resTypeValues;
	}
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
	public String getIsEncrypted() {
		return isEncrypted;
	}
	public void setIsEncrypted(String is_encrypted) {
		this.isEncrypted = is_encrypted;
	}

}
