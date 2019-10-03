package com.cbp.ticketing.model;

import java.util.Date;
import java.util.List;

public class TicketRole {
	private int roleId;
	private String roleName;
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private String isDeleted;
	private List<Integer> optionTypeIds;
	private List<Integer> deletedTypeIds;
	private List<OptionType> assign;
	private List<OptionType> unassign;
	public List<OptionType> getAssign() {
		return assign;
	}
	public void setAssign(List<OptionType> assign) {
		this.assign = assign;
	}
	public List<OptionType> getUnassign() {
		return unassign;
	}
	public void setUnassign(List<OptionType> unassign) {
		this.unassign = unassign;
	}
	private TicketRoleTypeOption ticketRoleTypeOption;
	public List<Integer> getOptionTypeIds() {
		return optionTypeIds;
	}
	public void setOptionTypeIds(List<Integer> optionTypeIds) {
		this.optionTypeIds = optionTypeIds;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public Date getDeletedDate() {
		return deletedDate;
	}
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * @return the ticketRoleTypeOption
	 */
	public TicketRoleTypeOption getTicketRoleTypeOption() {
		return ticketRoleTypeOption;
	}
	/**
	 * @param ticketRoleTypeOption the ticketRoleTypeOption to set
	 */
	public void setTicketRoleTypeOption(TicketRoleTypeOption ticketRoleTypeOption) {
		this.ticketRoleTypeOption = ticketRoleTypeOption;
	}
	/**
	 * @return the deletedTypeIds
	 */
	public List<Integer> getDeletedTypeIds() {
		return deletedTypeIds;
	}
	/**
	 * @param deletedTypeIds the deletedTypeIds to set
	 */
	public void setDeletedTypeIds(List<Integer> deletedTypeIds) {
		this.deletedTypeIds = deletedTypeIds;
	}

}
