package com.cbp.ticketing.model;

import java.util.Date;
import java.util.List;

public class TicketResSubType {

	private int resSubTypeId;
	private String resSubTypeName;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedts;
	private String isdeleted;
	private int sortKey;
	public int getResSubTypeId() {
		return resSubTypeId;
	}
	public void setResSubTypeId(int resSubTypeId) {
		this.resSubTypeId = resSubTypeId;
	}
	public String getResSubTypeName() {
		return resSubTypeName;
	}
	public void setResSubTypeName(String resSubTypeName) {
		this.resSubTypeName = resSubTypeName;
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
	public Date getDeletedts() {
		return deletedts;
	}
	public void setDeletedts(Date deletedts) {
		this.deletedts = deletedts;
	}
	public String getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}
	public int getSortKey() {
		return sortKey;
	}
	public void setSortKey(int sortKey) {
		this.sortKey = sortKey;
	}
	
	
}
