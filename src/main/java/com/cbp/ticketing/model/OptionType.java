package com.cbp.ticketing.model;

import java.util.Date;

public class OptionType {
	private int opTypeId; 
	private String opTypeName;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String isDeleted;
	public int getOpTypeId() {
		return opTypeId;
	}
	public void setOpTypeId(int opTypeId) {
		this.opTypeId = opTypeId;
	}
	public String getOpTypeName() {
		return opTypeName;
	}
	public void setOpTypeName(String opTypeName) {
		this.opTypeName = opTypeName;
	}
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
}
