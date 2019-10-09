package com.cbp.ticketing.dao.daoService;

import java.sql.SQLException;
import java.util.List;

import com.cbp.ticketing.model.OptionType;
import com.cbp.ticketing.model.TicketApp;
import com.cbp.ticketing.model.TicketEnvResTeamApp;
import com.cbp.ticketing.model.TicketEnvType;
import com.cbp.ticketing.model.TicketResCredentials;
import com.cbp.ticketing.model.TicketResSubType;
import com.cbp.ticketing.model.TicketResource;
import com.cbp.ticketing.model.TicketResourceType;
import com.cbp.ticketing.model.TicketRole;
import com.cbp.ticketing.model.TicketTeam;
import com.cbp.ticketing.model.TicketUser;
import com.cbp.ticketing.model.UserLogin;

public interface TicketingDaoService {
	public List<TicketUser> login(TicketUser user);

	public List<TicketUser> getTicketUserList();

	public void deletedUser(TicketUser ticketUser);

	public void updateUser(TicketUser ticketUser);

	// TeamApp
	public void createTicketApp(TicketApp ticketApp);

	public List<TicketApp> getTicketAppList();

	public boolean updateTicketApp(TicketApp ticketApp);

	public boolean deleteTicketApp(TicketApp ticketApp) throws SQLException;

	// TicketTeam
	/*
	 * public void createTicketTeam(TicketTeam ticketTeam);
	 * 
	 * public List<TicketTeam> getTicketTeamList();
	 * 
	 * public void updateTicketTeam(TicketTeam ticketTeam);
	 * 
	 * public void deleteTicketTeam(TicketTeam ticketTeam);
	 */

	// TicketResource
	public void createTicketRes(TicketResource ticketResource);

	//public List<TicketResource> getTicketResourceList();

	public void updateTicketResource(TicketResource ticketRes);

	public void deleteTicketResource(TicketResource ticketRes);
	// TicketResourceType

	//public void createTicketResType(TicketResourceType ticketResourceType);

	public List<TicketResourceType> getTicketResorceTypeList();

	public void updateTicketResourceType(TicketResourceType ticketResType);

	public void deleteTicketResourceType(TicketResourceType ticketResType);

	// TicketEnvType
	public void createTicketEnvType(TicketEnvType ticketEnvType);

	public void deleteTicketEnvType(TicketEnvType ticketEnvType);

	public void updateTicketEnvType(TicketEnvType ticketEnvType);

	public List<TicketEnvType> getTicketEnvType();

	public List<TicketApp> getAppNames(TicketTeam ticketTeam);

	public List<TicketEnvType> getEnvTypes(TicketEnvResTeamApp ticketEnvResTeamApp);

	public List<TicketResourceType> getResNames(TicketEnvResTeamApp ticketEnvResTeamApp);

	public List<TicketResCredentials> getResCredentials(TicketResource ticketResource);

	public void createTicketResSubType(TicketResSubType ticketResSubType);

	public List<TicketResSubType> getTicketResSubTypeList();

	public void createTicketUser(TicketUser ticketUser);

	public List<OptionType> getListOfOptionTypes();

	public void createTicketRole(TicketRole ticketRole);

	public List<OptionType> getSeletedOptionTypes(TicketRole ticketRole);

	public void UpdateRoleTypes(TicketRole ticketRole);

	public List<TicketRole> getListRoles();

	public void deletedRole(TicketRole ticketRole);
	public List<OptionType> getIsDeleted(TicketRole ticketRole);

}
