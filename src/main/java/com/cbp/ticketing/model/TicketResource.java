package com.cbp.ticketing.model;

import java.util.Date;
import java.util.List;

public class TicketResource {
	private int resId;
	private String resName;
	private int resTypeId;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String isDeleted;
	private TicketResCredentials ticketResCredentials;
	private List<String> resTypeKeys;
	private List<String> resTypeValues;
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
	 * @return the resTypeKeys
	 */
	public List<String> getResTypeKeys() {
		return resTypeKeys;
	}
	/**
	 * @param resTypeKeys the resTypeKeys to set
	 */
	public void setResTypeKeys(List<String> resTypeKeys) {
		this.resTypeKeys = resTypeKeys;
	}
	/**
	 * @return the resTypeValues
	 */
	public List<String> getResTypeValues() {
		return resTypeValues;
	}
	/**
	 * @param resTypeValues the resTypeValues to set
	 */
	public void setResTypeValues(List<String> resTypeValues) {
		this.resTypeValues = resTypeValues;
	}
	
	
}
