package com.cbp.ticketing.model;

import java.util.Date;
import java.util.List;

public class TicketResourceType {
	private int resTypeId;
	private String resTypeName;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String isDeleted;

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

	public int getResTypeId() {
		return resTypeId;
	}

	public void setResTypeId(int resTypeId) {
		this.resTypeId = resTypeId;
	}

	public String getResTypeName() {
		return resTypeName;
	}

	public void setResTypeName(String resTypeName) {
		this.resTypeName = resTypeName;
	}

}
