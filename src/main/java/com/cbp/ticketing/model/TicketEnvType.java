package com.cbp.ticketing.model;

import java.util.Date;
import java.util.List;

public class TicketEnvType {
	private int envTypeId;
	private String envTypeName;
	private Date createdDate;
	private Date modifiedDate;
	private List envTypeNames;
	private Date deleted_ts;
	private String is_deleted;
	public String getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
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
	/**
	 * @return the envTypeNames
	 */
	public List getEnvTypeNames() {
		return envTypeNames;
	}
	/**
	 * @param envTypeNames the envTypeNames to set
	 */
	public void setEnvTypeNames(List envTypeNames) {
		this.envTypeNames = envTypeNames;
	}
	/**
	 * @return the deleted_ts
	 */
	public Date getDeleted_ts() {
		return deleted_ts;
	}
	/**
	 * @param deleted_ts the deleted_ts to set
	 */
	public void setDeleted_ts(Date deleted_ts) {
		this.deleted_ts = deleted_ts;
	}

}
