package com.cbp.ticketing.model;

public class TicketEnvResTeamApp {
	private int appId;
	private int teamId;
	private int envTypeId;
	private int resId;
		public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public int getEnvTypeId() {
		return envTypeId;
	}
	public void setEnvTypeId(int envTypeId) {
		this.envTypeId = envTypeId;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	/**
	 * @return the teamId
	 */
	public int getTeamId() {
		return teamId;
	}
	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
		
}
