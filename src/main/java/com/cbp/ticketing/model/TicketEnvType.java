package com.cbp.ticketing.model;

import java.util.Date;

public class TicketEnvType {
	private int envTypeId;
	private String envTypeName;
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
	public int getEnvTypeId() {
		return envTypeId;
	}
	public void setEnvTypeId(int envTypeId) {
		this.envTypeId = envTypeId;
	}
	public String getEnvTypeName() {
		return envTypeName;
	}
	public void setEnvTypeName(String envTypeName) {
		this.envTypeName = envTypeName;
	}

}
