package com.cbp.ticketing.model;

import java.util.Date;
import java.util.List;

public class TicketTeam {
	private int teamId;
	private String teamName;
	private Date createdDate;
	private Date modifiedDate;
	private List teamNames;
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
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	/**
	 * @return the teamNames
	 */
	public List getTeamNames() {
		return teamNames;
	}
	/**
	 * @param teamNames the teamNames to set
	 */
	public void setTeamNames(List teamNames) {
		this.teamNames = teamNames;
	}

}
