package com.cbp.ticketing.action.service;

import java.util.List;
import com.cbp.ticketing.model.TicketApp;
import com.cbp.ticketing.model.TicketEnvResTeamApp;
import com.cbp.ticketing.model.TicketEnvType;
import com.cbp.ticketing.model.TicketResCredentials;
import com.cbp.ticketing.model.TicketResource;
import com.cbp.ticketing.model.TicketResourceType;
import com.cbp.ticketing.model.TicketTeam;
import com.cbp.ticketing.model.UserLogin;

public interface TicketingService {
	public List<UserLogin>  login(UserLogin user);

//TeamApp 
	public void createTicketApp(TicketApp ticketApp);

	public List<TicketApp> getTicketAppList();

	public boolean updateTicketApp(TicketApp ticketApp);

	public boolean deleteTicketApp(TicketApp ticketApp);

//TicketTeam
	public void createTicketTeam(TicketTeam ticketTeam);

	public List<TicketTeam> getTicketTeamList();

	public void updateTicketTeam(TicketTeam ticketTeam);

	public void deleteTicketTeam(TicketTeam ticketTeam);

//TicketResource
	public void createTicketRes(TicketResource ticketResource);

	public List<TicketResource> getTicketResourceList();

	public void updateTicketResource(TicketResource ticketRes);

	public void deleteTicketResource(TicketResource ticketRes);
//TicketResourceType

	public void createTicketResType(TicketResourceType ticketResourceType);

	public List<TicketResourceType> getTicketResorceTypeList();

	public void updateTicketResourceType(TicketResourceType ticketResType);

	public void deleteTicketResourceType(TicketResourceType ticketResType);

//TicketEnvType
	public void createTicketEnvType(TicketEnvType ticketEnvType);

	public void deleteTicketEnvType(TicketEnvType ticketEnvType);

	public void updateTicketEnvType(TicketEnvType ticketEnvType);

	public List<TicketEnvType> getTicketEnvType();
	public List<TicketApp> getAppNames(TicketTeam ticketTeam);
	public List<TicketEnvType> getEnvTypes(TicketEnvResTeamApp ticketEnvResTeamApp);
	public List<TicketResource> getResNames(TicketEnvResTeamApp ticketEnvResTeamApp);
	public List<TicketResCredentials> getResCredentials(TicketResource ticketResource);
}
