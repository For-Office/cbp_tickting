package com.cbp.ticketing.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ExcelData {
	
	ArrayList<Team> teams;
	ArrayList<Resource> resources;
	ArrayList<Environment> environments;
	ArrayList<Application> applications;
	
	HashMap cellValues=new HashMap();
	HashMap header=new HashMap();
	
	public ArrayList<Team> getTeams() {
		return teams;
	}
	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}
	public ArrayList<Resource> getResources() {
		return resources;
	}
	public void setResources(ArrayList<Resource> resources) {
		this.resources = resources;
	}
	public ArrayList<Environment> getEnvironments() {
		return environments;
	}
	public void setEnvironments(ArrayList<Environment> environments) {
		this.environments = environments;
	}
	public ArrayList<Application> getApplications() {
		return applications;
	}
	public void setApplications(ArrayList<Application> applications) {
		this.applications = applications;
	}
	
}
