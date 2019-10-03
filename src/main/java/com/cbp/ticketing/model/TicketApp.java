package com.cbp.ticketing.model;

import java.util.Date;
import java.util.List;

public class TicketApp {
	private int appId;
	private String appName;
	private  List appNames;
	private Date createdDate;
	private Date modifiedDate;
	private Date deleted_ts;
	private String is_deleted;
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public String getAppName() {
		return appName;
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
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
	/**
	 * @return the appNames
	 */
	public List getAppNames() {
		return appNames;
	}
	/**
	 * @param appNames the appNames to set
	 */
	public void setAppNames(List appNames) {
		this.appNames = appNames;
	}
	
	public String getIs_deleted() {
		return is_deleted;
	}
	/**
	 * @param is_deleted the is_deleted to set
	 */
	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
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
