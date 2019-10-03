package com.cbp.ticketing.model;

public class TicketUser {
	private int userId;
	private String userName;
	private String password;
	private String isSiteAdmin;
	private String emailId;
	private String isDeleted;
	private String isLocked;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsSiteAdmin() {
		return isSiteAdmin;
	}
	public void setIsSiteAdmin(String isSiteAdmin) {
		this.isSiteAdmin = isSiteAdmin;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}

}
