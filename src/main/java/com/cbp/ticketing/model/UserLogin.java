package com.cbp.ticketing.model;

public class UserLogin {
	private String userName;
	private String password;
	private String emailID;

	/*public UserLogin(String userName, String password) {
		// TODO Auto-generated constructor stub
		this.userName=userName;
		this.password=password;
	}
*/
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

	/**
	 * @return the emailID
	 */
	public String getEmailID() {
		return emailID;
	}

	/**
	 * @param emailID the emailID to set
	 */
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

}
