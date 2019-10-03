package com.cbp.ticketing.model;

import java.util.Date;

public class TicketRoleTypeOption {
	
	private int roleId;
	private int optionTypeId;
	private String isLocked;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String isDeleted;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getOptionTypeId() {
		return optionTypeId;
	}
	public void setOptionTypeId(int optionTypeId) {
		this.optionTypeId = optionTypeId;
	}
	public String getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
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
