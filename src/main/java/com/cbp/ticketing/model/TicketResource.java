package com.cbp.ticketing.model;

import java.util.Date;

public class TicketResource {
	private int resId;
	private String resName;
	private int resTypeId;
	private Date createdDate;
	private Date modifiedDate;
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
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public int getResTypeId() {
		return resTypeId;
	}
	public void setResTypeId(int resTypeId) {
		this.resTypeId = resTypeId;
	}

}