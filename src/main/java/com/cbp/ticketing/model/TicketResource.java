package com.cbp.ticketing.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class TicketResource {
	private int resId;
	private String resTypeName;
	private int resTypeId;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String isDeleted;
	private TicketResCredentials ticketResCredentials;
	private Map<String,String> keysAndValues;
	public Map<String, String> getKeysAndValues() {
		return keysAndValues;
	}
	public void setKeysAndValues(Map<String, String> keysAndValues) {
		this.keysAndValues = keysAndValues;
	}
	private List resNames;
	private List<Integer> resTypeIds;
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
	
	public int getResTypeId() {
		return resTypeId;
	}
	public void setResTypeId(int resTypeId) {
		this.resTypeId = resTypeId;
	}
	/**
	 * @return the resNames
	 */
	public List getResNames() {
		return resNames;
	}
	/**
	 * @param resNames the resNames to set
	 */
	public void setResNames(List resNames) {
		this.resNames = resNames;
	}
	/**
	 * @return the resTypeIds
	 */
	public List<Integer> getResTypeIds() {
		return resTypeIds;
	}
	/**
	 * @param resTypeIds the resTypeIds to set
	 */
	public void setResTypeIds(List<Integer> resTypeIds) {
		this.resTypeIds = resTypeIds;
	}
	/**
	 * @return the isDeleted
	 */
	public String getIsDeleted() {
		return isDeleted;
	}
	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * @return the deletedDate
	 */
	public Date getDeletedDate() {
		return deletedDate;
	}
	/**
	 * @param deletedDate the deletedDate to set
	 */
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}
	public TicketResCredentials getTicketResCredentials() {
		return ticketResCredentials;
	}
	public void setTicketResCredentials(TicketResCredentials ticketResCredentials) {
		this.ticketResCredentials = ticketResCredentials;
	}
	/**
	 * @return the resTypeName
	 */
	public String getResTypeName() {
		return resTypeName;
	}
	/**
	 * @param resTypeName the resTypeName to set
	 */
	public void setResTypeName(String resTypeName) {
		this.resTypeName = resTypeName;
	}
	
	
}
