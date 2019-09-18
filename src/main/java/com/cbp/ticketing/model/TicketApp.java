package com.cbp.ticketing.model;

import java.util.Date;
import java.util.List;

public class TicketApp {
	private int appId;
	private String appName;
	private  List appNames;
	private Date createdDate;
	private Date modifiedDate;
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
	
	

}
