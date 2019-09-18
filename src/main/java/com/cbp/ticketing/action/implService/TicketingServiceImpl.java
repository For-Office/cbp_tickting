package com.cbp.ticketing.action.implService;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cbp.ticketing.action.service.TicketingService;
import com.cbp.ticketing.dao.daoService.TicketingDaoService;
import com.cbp.ticketing.model.TicketApp;
import com.cbp.ticketing.model.TicketEnvResTeamApp;
import com.cbp.ticketing.model.TicketEnvType;
import com.cbp.ticketing.model.TicketResCredentials;
import com.cbp.ticketing.model.TicketResource;
import com.cbp.ticketing.model.TicketResourceType;
import com.cbp.ticketing.model.TicketTeam;
import com.cbp.ticketing.model.UserLogin;

@Service
public class TicketingServiceImpl implements TicketingService {
	@Autowired
	TicketingDaoService service;;

	public List<UserLogin> login(UserLogin user) {
		System.out.println("Service");
		List<UserLogin> users = service.login(user);
		return users;
	}

//TicketApp 
	public void createTicketApp(TicketApp ticketApp) {
		ticketApp.setCreatedDate(new Date());
		ticketApp.setModifiedDate(new Date());
		service.createTicketApp(ticketApp);
	}

	public List<TicketApp> getTicketAppList() {
		List<TicketApp> list = service.getTicketAppList();
		return list;
	}

	public boolean updateTicketApp(TicketApp ticketApp) {
		ticketApp.setModifiedDate(new Date());
		boolean flag = service.updateTicketApp(ticketApp);
		return flag;
	}

	public boolean deleteTicketApp(TicketApp ticketApp) {
		boolean flag = service.deleteTicketApp(ticketApp);
		return flag;
	}

//TicketTeam
	public void createTicketTeam(TicketTeam ticketTeam) {
		ticketTeam.setCreatedDate(new Date());
		ticketTeam.setModifiedDate(new Date());
		service.createTicketTeam(ticketTeam);
	}

	public List<TicketTeam> getTicketTeamList() {
		List<TicketTeam> list = service.getTicketTeamList();
		return list;

	}

	public void updateTicketTeam(TicketTeam ticketTeam) {
		ticketTeam.setModifiedDate(new Date());
		service.updateTicketTeam(ticketTeam);
	}
	

	public void deleteTicketTeam(TicketTeam ticketTeam) {
		service.deleteTicketTeam(ticketTeam);
	}

//TicketRes 
	public void createTicketRes(TicketResource ticketResource) {
		ticketResource.setCreatedDate(new Date());
		ticketResource.setModifiedDate(new Date());
		service.createTicketRes(ticketResource);

	}

	public List<TicketResource> getTicketResourceList() {

		List<TicketResource> list = service.getTicketResourceList();
		return list;

	}

	public void updateTicketResource(TicketResource ticketRes) {
		ticketRes.setModifiedDate(new Date());
		service.updateTicketResource(ticketRes);
	}

	public void deleteTicketResource(TicketResource ticketRes) {
		service.deleteTicketResource(ticketRes);
	}

//TicketResType
	public void createTicketResType(TicketResourceType ticketResourceType) {
		ticketResourceType.setCreatedDate(new Date());
		ticketResourceType.setModifiedDate(new Date());
		service.createTicketResType(ticketResourceType);
	}

	public List<TicketResourceType> getTicketResorceTypeList() {
		List<TicketResourceType> list = service.getTicketResorceTypeList();
		return list;
	}

	public void updateTicketResourceType(TicketResourceType ticketResType) {
		ticketResType.setModifiedDate(new Date());
		service.updateTicketResourceType(ticketResType);
	}

	public void deleteTicketResourceType(TicketResourceType ticketResType) {
		service.deleteTicketResourceType(ticketResType);
	}

//TicketEnvType
	public void createTicketEnvType(TicketEnvType ticketEnvType) {
		ticketEnvType.setCreatedDate(new Date());
		ticketEnvType.setModifiedDate(new Date());
		service.createTicketEnvType(ticketEnvType);
	}

	public void updateTicketEnvType(TicketEnvType ticketEnvType) {

		ticketEnvType.setModifiedDate(new Date());
		service.updateTicketEnvType(ticketEnvType);
	}

	public List<TicketEnvType> getTicketEnvType() {

		List<TicketEnvType> list = service.getTicketEnvType();
		return list;

	}

	public void deleteTicketEnvType(TicketEnvType ticketEnvType) {
		service.deleteTicketEnvType(ticketEnvType);

	}

	public List<TicketApp> getAppNames(TicketTeam ticketTeam) {
		List<TicketApp> list = service.getAppNames(ticketTeam);
		return list;
	}

	public List<TicketEnvType> getEnvTypes(TicketEnvResTeamApp ticketEnvResTeamApp) {
		List<TicketEnvType> list = service.getEnvTypes(ticketEnvResTeamApp);
		return list;
	}

	public List<TicketResource> getResNames(TicketEnvResTeamApp ticketEnvResTeamApp) {
		List<TicketResource> list = service.getResNames(ticketEnvResTeamApp);
		return list;
	}
public List<TicketResCredentials> getResCredentials(TicketResource ticketResource){
	List<TicketResCredentials> list = service.getResCredentials(ticketResource);
	return list;
		
	}
}
