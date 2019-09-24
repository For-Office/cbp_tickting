package com.cbp.ticketing.model;

import java.util.Date;
import java.util.List;

public class TicketResourceType {
	private int resTypeId;
	private String resTypeName;
	private Date createdDate;
	private Date modifiedDate;
	private List resTypeNames;
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
	/**
	 * @return the resTypeNames
	 */
	public List getResTypeNames() {
		return resTypeNames;
	}
	/**
	 * @param resTypeNames the resTypeNames to set
	 */
	public void setResTypeNames(List resTypeNames) {
		this.resTypeNames = resTypeNames;
	}
	
	
}
