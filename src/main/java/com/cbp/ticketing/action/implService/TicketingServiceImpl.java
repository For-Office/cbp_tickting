package com.cbp.ticketing.action.implService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbp.ticketing.Utility.ReUtils;
import com.cbp.ticketing.action.service.TicketingService;
import com.cbp.ticketing.dao.daoService.TicketingDaoService;
import com.cbp.ticketing.model.OptionType;
import com.cbp.ticketing.model.TicketApp;
import com.cbp.ticketing.model.TicketEnvResTeamApp;
import com.cbp.ticketing.model.TicketEnvType;
import com.cbp.ticketing.model.TicketResCredentials;
import com.cbp.ticketing.model.TicketResSubType;
import com.cbp.ticketing.model.TicketResource;
import com.cbp.ticketing.model.TicketResourceType;
import com.cbp.ticketing.model.TicketRole;
import com.cbp.ticketing.model.TicketRoleTypeOption;
import com.cbp.ticketing.model.TicketTeam;
import com.cbp.ticketing.model.TicketUser;
import com.cbp.ticketing.model.UserLogin;

@Service
public class TicketingServiceImpl implements TicketingService {
	@Autowired
	TicketingDaoService service;;

	public List<TicketUser> login(TicketUser user) {
		user.setPassword(ReUtils.encryptPassword(user.getPassword()));
		List<TicketUser> users = service.login(user);
		return users;
	}

	public void createTicketUser(TicketUser ticketUser) {
		ticketUser.setPassword(ReUtils.encryptPassword(ticketUser.getPassword()));
		service.createTicketUser(ticketUser);
	}

	public List<TicketUser> getTicketUserList() {
		List list = service.getTicketUserList();
		return list;
	}

	public void deletedUser(TicketUser ticketUser) {
		ticketUser.setIsDeleted("Y");
		service.deletedUser(ticketUser);
	}

	public void updateUser(TicketUser ticketUser) {
		service.updateUser(ticketUser);
	}

//TicketApp 
	public void createTicketApp(TicketApp ticketApp) {
		ticketApp.setCreatedDate(new Date());
		ticketApp.setModifiedDate(new Date());
		ticketApp.setDeleted_ts(new Date());
		ticketApp.setIs_deleted("N");
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

	public boolean deleteTicketApp(TicketApp ticketApp) throws SQLException {
		ticketApp.setDeleted_ts(new Date());
		ticketApp.setIs_deleted("Y");
		boolean flag = service.deleteTicketApp(ticketApp);
		return flag;
	}

//TicketTeam
	/*
	 * public void createTicketTeam(TicketTeam ticketTeam) {
	 * ticketTeam.setCreatedDate(new Date()); ticketTeam.setModifiedDate(new
	 * Date()); service.createTicketTeam(ticketTeam); }
	 * 
	 * public List<TicketTeam> getTicketTeamList() { List<TicketTeam> list =
	 * service.getTicketTeamList(); return list;
	 * 
	 * }
	 * 
	 * public void updateTicketTeam(TicketTeam ticketTeam) {
	 * ticketTeam.setModifiedDate(new Date()); service.updateTicketTeam(ticketTeam);
	 * }
	 * 
	 * 
	 * public void deleteTicketTeam(TicketTeam ticketTeam) {
	 * service.deleteTicketTeam(ticketTeam); }
	 */
//TicketRes 
	public void createTicketRes(TicketResource ticketResource) {
		ticketResource.setCreatedDate(new Date());
		ticketResource.setModifiedDate(new Date());
		ticketResource.setDeletedDate(new Date());
		ticketResource.setIsDeleted("N");
		TicketResCredentials ticketResCredentials = new TicketResCredentials();
		ticketResCredentials.setCreatedDate(new Date());
		ticketResCredentials.setModifiedDate(new Date());
		ticketResCredentials.setDeletedDate(new Date());

		ticketResCredentials.setIsDeleted("N");
		ticketResCredentials.setIsEncrypted("N");
		ticketResource.setTicketResCredentials(ticketResCredentials);
		service.createTicketRes(ticketResource);

	}

	/* */

	public void updateTicketResource(TicketResource ticketRes) {
		ticketRes.setModifiedDate(new Date());
		service.updateTicketResource(ticketRes);
	}

	public void deleteTicketResource(TicketResource ticketRes) {
		service.deleteTicketResource(ticketRes);
	}

//TicketResType
	/*public void createTicketResType(TicketResourceType ticketResourceType) {
		ticketResourceType.setCreatedDate(new Date());
		ticketResourceType.setModifiedDate(new Date());
		ticketResourceType.setDeletedDate(new Date());
		ticketResourceType.setIsDeleted("N");
		service.createTicketResType(ticketResourceType);
	}*/

	public List<TicketResourceType> getTicketResorceTypeList() {
		List<TicketResourceType> list = service.getTicketResorceTypeList();
		return list;
	}

	public void updateTicketResourceType(TicketResourceType ticketResType) {
		ticketResType.setModifiedDate(new Date());
		service.updateTicketResourceType(ticketResType);
	}

	public void deleteTicketResourceType(TicketResourceType ticketResourceType) {
		ticketResourceType.setDeletedDate(new Date());
		service.deleteTicketResourceType(ticketResourceType);
	}

//TicketEnvType
	public void createTicketEnvType(TicketEnvType ticketEnvType) {
		ticketEnvType.setCreatedDate(new Date());
		ticketEnvType.setModifiedDate(new Date());
		ticketEnvType.setDeleted_ts(new Date());
		ticketEnvType.setIs_deleted("N");
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
		ticketEnvType.setDeleted_ts(new Date());
		ticketEnvType.setIs_deleted("Y");
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

	public List<TicketResCredentials> getResCredentials(TicketResource ticketResource) {
		List<TicketResCredentials> list = service.getResCredentials(ticketResource);
		return list;

	}

	public void createTicketResSubType(TicketResSubType ticketResSubType) {
		ticketResSubType.setCreatedDate(new Date());
		ticketResSubType.setModifiedDate(new Date());
		ticketResSubType.setIsdeleted("N");
		service.createTicketResSubType(ticketResSubType);
	}

	public List<TicketResSubType> getTicketResSubTypeList() {
		List<TicketResSubType> list = service.getTicketResSubTypeList();

		return list;

	}

	public List<OptionType> getListOfOptionTypes() {

		List<OptionType> list = service.getListOfOptionTypes();

		return list;
	}

	public void createTicketRole(TicketRole ticketRole) {
		ticketRole.setCreatedDate(new Date());
		ticketRole.setModifiedDate(new Date());
		ticketRole.setDeletedDate(new Date());
		ticketRole.setIsDeleted("N");
		TicketRoleTypeOption ticketRoleTypeOption = new TicketRoleTypeOption();
		ticketRoleTypeOption.setCreatedDate(new Date());
		ticketRoleTypeOption.setModifiedDate(new Date());
		ticketRoleTypeOption.setDeletedDate(new Date());
		// ticketRoleTypeOption.setIsDeleted("N");
		ticketRoleTypeOption.setIsLocked("N");
		ticketRole.setTicketRoleTypeOption(ticketRoleTypeOption);
		service.createTicketRole(ticketRole);
	}

	public List<OptionType> getSeletedOptionTypes(TicketRole ticketRole) {
		List<OptionType> list = service.getSeletedOptionTypes(ticketRole);
		return list;

	}

	public void UpdateRoleTypes(TicketRole ticketRole) {
		ticketRole.setCreatedDate(new Date());
		ticketRole.setModifiedDate(new Date());
		ticketRole.setDeletedDate(new Date());
		// ticketRole.setIsDeleted("N");
		TicketRoleTypeOption ticketRoleTypeOption = new TicketRoleTypeOption();
		ticketRoleTypeOption.setCreatedDate(new Date());
		ticketRoleTypeOption.setModifiedDate(new Date());
		ticketRoleTypeOption.setDeletedDate(new Date());
		// ticketRoleTypeOption.setIsDeleted("N");
		ticketRoleTypeOption.setIsLocked("N");
		ticketRole.setTicketRoleTypeOption(ticketRoleTypeOption);
		service.UpdateRoleTypes(ticketRole);
	}

	public List<TicketRole> getListRoles() {
		List<TicketRole> list = service.getListRoles();
		return list;
	}

	public void deletedRole(TicketRole ticketRole) {
		ticketRole.setDeletedDate(new Date());
		ticketRole.setIsDeleted("Y");
		service.deletedRole(ticketRole);
	}
	public List<OptionType> getIsDeleted(TicketRole ticketRole){
		List<OptionType> list = service.getIsDeleted(ticketRole);
		return list;
	}
}
