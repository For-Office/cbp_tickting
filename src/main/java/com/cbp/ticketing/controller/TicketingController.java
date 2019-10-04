package com.cbp.ticketing.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cbp.ticketing.action.implService.TicketingServiceImpl;
import com.cbp.ticketing.common.ErrorCodes;
import com.cbp.ticketing.model.OptionType;
import com.cbp.ticketing.model.ResponseBodyHelper;
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

@Lazy
@RestController
@RequestMapping(path = "/ticketing")
public class TicketingController {
	private static final Logger logger = LoggerFactory.getLogger(TicketingController.class);
	@Autowired
	TicketingServiceImpl ticket;
	@Autowired
	ErrorCodes errorcodes;

	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody TicketUser user) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		logger.info("Login User Name" + user.getUserName() + "password" + user.getPassword());
		List<TicketUser> users = ticket.login(user);
		if (users.size() > 0) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage("user is login successfuly");
			responseBody.setResult(users);

		} else {
			responseBody.setStatusCode(String.valueOf(HttpStatus.NON_AUTHORITATIVE_INFORMATION));
			responseBody.setReqStatus("failed");
			System.out.println("........" + errorcodes.getERRORS_LOOKUP_UNKNOWN());
			responseBody.setMessage(errorcodes.getERRORS_LOOKUP_UNKNOWN());
		}
		return ResponseEntity.ok(responseBody);

	}

//TicketApp Controller
	@PostMapping(path = "/saveTicketApp")
	public ResponseEntity<?> saveTicketApp(@RequestBody TicketApp ticketApp) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		ticketApp.getAppNames();
		logger.info("List of TicketAppNames.." + ticketApp.getAppNames());
		ticket.createTicketApp(ticketApp);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage(errorcodes.getERRORS_NEWAPPLICATIONCREATION_SUCCESS());
		return ResponseEntity.ok(responseBody);
	}

	@GetMapping(path = "/getTicketAppList")
	public ResponseEntity<ResponseBodyHelper> getTicketAppList() {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		List<TicketApp> ticketAppList = new ArrayList<TicketApp>();
		ticketAppList = ticket.getTicketAppList();
		logger.info("displaying ticketApplication List ...." + ticketAppList);

		if (ticketAppList.size() > 0) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage("TicketAppList is found");
			responseBody.setResult(ticketAppList);
		} else {
			responseBody.setReqStatus("failed");
			responseBody.setMessage("TicketAppList is not found");
		}

		return ResponseEntity.ok(responseBody);

	}

	@PostMapping(path = "/updateTicketApp")
	public ResponseEntity<?> updateTicketAppList(@RequestBody TicketApp ticketApp) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		boolean flag = ticket.updateTicketApp(ticketApp);
		if (flag) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage(errorcodes.getERRORS_APPLICATIONUPDATED_SUCCES());
		} else {
			responseBody.setReqStatus("failed");
			responseBody.setMessage("updated failed");
		}
		return ResponseEntity.ok(responseBody);
	}

	@PostMapping(path = "/deleteTicketApp")
	public ResponseEntity<?> deleteTicketApp(@RequestBody TicketApp ticketApp) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		try {
			ticket.deleteTicketApp(ticketApp);
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage(errorcodes.getERRORS_DELETEAPPLICATION_SUCCES());
		} catch (SQLException sqlException) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("failed");
			responseBody.setMessage(errorcodes.getERRORS_DELETEAPPLICATION_FAILURE());
		}
		return ResponseEntity.ok(responseBody);
	}

//TicketTeam Controllers
	/*
	 * @PostMapping(path = "/saveTicketTeam") public ResponseEntity<?>
	 * saveTicketTeam(@RequestBody TicketTeam ticketTeam) { ResponseBodyHelper
	 * responseBody = new ResponseBodyHelper(); ticket.createTicketTeam(ticketTeam);
	 * responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
	 * responseBody.setReqStatus("success");
	 * responseBody.setMessage(errorcodes.getERRORS_NEWTEAMCREATION_SUCCESS());
	 * return ResponseEntity.ok(responseBody); }
	 * 
	 * @GetMapping(path = "/getTicketTeamList") public ResponseEntity<?>
	 * getTicketTeamList() { ResponseBodyHelper responseBody = new
	 * ResponseBodyHelper(); List<TicketTeam> ticketAppList = new
	 * ArrayList<TicketTeam>(); ticketAppList = ticket.getTicketTeamList(); if
	 * (ticketAppList.size() > 0) {
	 * responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
	 * responseBody.setReqStatus("success");
	 * responseBody.setMessage("TicketTeamList is found");
	 * responseBody.setResult(ticketAppList); } else {
	 * responseBody.setReqStatus("failed");
	 * responseBody.setMessage("TicketTeamList is not found"); }
	 * 
	 * return ResponseEntity.ok(responseBody);
	 * 
	 * }
	 * 
	 * @PostMapping(path = "/updateTicketTeam") public ResponseEntity<?>
	 * updateTicketTeam(@RequestBody TicketTeam ticketTeam) { ResponseBodyHelper
	 * responseBody = new ResponseBodyHelper();
	 * System.out.println(ticketTeam.getTeamName());
	 * ticket.updateTicketTeam(ticketTeam);
	 * responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
	 * responseBody.setReqStatus("success");
	 * responseBody.setMessage(errorcodes.getERRORS_NEWTEAMUPDATED_SUCCESS());
	 * return ResponseEntity.ok(responseBody); }
	 * 
	 * @PostMapping(path = "/deleteTicketTeam") public ResponseEntity<?>
	 * deleteTicketTeam(@RequestBody TicketTeam ticketTeam) { ResponseBodyHelper
	 * responseBody = new ResponseBodyHelper(); ticket.deleteTicketTeam(ticketTeam);
	 * responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
	 * responseBody.setReqStatus("success");
	 * responseBody.setMessage(errorcodes.getERRORS_DELETEAPPLICATION_SUCCES());
	 * return ResponseEntity.ok(responseBody);
	 * 
	 * }
	 */

	// TicketRes Controllers
	@PostMapping(path = "/saveTicketRes")
	public ResponseEntity<?> saveTicketRes(@RequestBody TicketResource ticketResource) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();

		ticket.createTicketRes(ticketResource);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage(errorcodes.getERRORS_NEWRESOURCECREATION_SUCCESS());
		return ResponseEntity.ok(responseBody);
	}

	@GetMapping(path = "/getTicketResourceList")
	public ResponseEntity<?> getTicketResourceList() {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		List<TicketResource> ticketResorceTypeList = new ArrayList<TicketResource>();
		ticketResorceTypeList = ticket.getTicketResourceList();
		if (ticketResorceTypeList.size() > 0) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage("TicketResourceTypeList is found");
			responseBody.setResult(ticketResorceTypeList);
		} else {
			responseBody.setReqStatus("failed");
			responseBody.setMessage("TicketResourceTypeList is not found");
		}

		return ResponseEntity.ok(responseBody);
	}

	@PostMapping(path = "/updateTicketResource")
	public ResponseEntity<?> updateTicketResource(@RequestBody TicketResource ticketRes) {
		System.out.println(ticketRes.getResName());
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		ticket.updateTicketResource(ticketRes);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage(errorcodes.getERRORS_RESOURCEUPDATED_SUCCESS());
		return ResponseEntity.ok(responseBody);
	}

	@PostMapping(path = "/deleteTicketResource")
	public ResponseEntity<?> deleteTicketResource(@RequestBody TicketResource ticketRes) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		ticket.deleteTicketResource(ticketRes);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage(errorcodes.getERRORS_DELETERESOURCE_SUCCESS());
		return ResponseEntity.ok(responseBody);

	}

	// TicketEnvType Controllers

	@PostMapping(path = "/saveTicketEnvType")
	public ResponseEntity<?> createTicketEnvType(@RequestBody TicketEnvType ticketEnvType) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();

		ticket.createTicketEnvType(ticketEnvType);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage(errorcodes.getCREATE_ENVIRONMENT_SUCCESS());
		return ResponseEntity.ok(responseBody);
	}

	@GetMapping(path = "/getTicketEnvType")
	public ResponseEntity<?> getTicketEnvType() {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		List<TicketEnvType> ticketEnvTypeList = new ArrayList<TicketEnvType>();
		ticketEnvTypeList = ticket.getTicketEnvType();
		if (ticketEnvTypeList.size() > 0) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage("TicketResourceTypeList is found");
			responseBody.setResult(ticketEnvTypeList);
		} else {
			responseBody.setReqStatus("failed");
			responseBody.setMessage("TicketResourceTypeList is not found");
		}

		return ResponseEntity.ok(responseBody);
	}

	@PostMapping(path = "/updateTicketEnvType")
	public ResponseEntity<?> updateTicketEnvType(@RequestBody TicketEnvType ticketEnvType) {

		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		ticket.updateTicketEnvType(ticketEnvType);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage(errorcodes.getUPDATE_ENVIRONMENT_SUCCESS());
		return ResponseEntity.ok(responseBody);
	}

	@PostMapping(path = "/deleteTicketEnvType")
	public ResponseEntity<?> deleteTicketEnvType(@RequestBody TicketEnvType ticketEnvType) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		ticket.deleteTicketEnvType(ticketEnvType);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage(errorcodes.getDELETE_ENVIRONMENT_SUCCESS());
		return ResponseEntity.ok(responseBody);

	}

	// TicketResType Controllers

	@PostMapping(path = "/saveTicketResType")
	public ResponseEntity<?> saveTicketResType(@RequestBody TicketResourceType ticketResourceType) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();

		ticket.createTicketResType(ticketResourceType);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("TicketResType Record Saved");
		return ResponseEntity.ok(responseBody);
	}

	@GetMapping(path = "/getTicketResourceTypeList")
	public ResponseEntity<?> getTicketResourceTypeList() {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		List<TicketResourceType> ticketResorceTypeList = new ArrayList<TicketResourceType>();
		ticketResorceTypeList = ticket.getTicketResorceTypeList();
		if (ticketResorceTypeList.size() > 0) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage("TicketResourceTypeList is found");
			responseBody.setResult(ticketResorceTypeList);
		} else {
			responseBody.setReqStatus("failed");
			responseBody.setMessage("TicketResourceTypeList is not found");
		}

		return ResponseEntity.ok(responseBody);

	}

	@PostMapping(path = "/updateTicketResourceType")
	public ResponseEntity<?> updateTicketResourceType(@RequestBody TicketResourceType ticketResType) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		// System.out.println(ticketResType.getResTypeName());
		ticket.updateTicketResourceType(ticketResType);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("updated successfuly");
		return ResponseEntity.ok(responseBody);
	}

	@PostMapping(path = "/deleteTicketResourceType")
	public ResponseEntity<?> deleteTicketResourceType(@RequestBody TicketResourceType ticketResType) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		ticket.deleteTicketResourceType(ticketResType);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("TicketResourceType is deleted");
		return ResponseEntity.ok(responseBody);

	}

	@PostMapping(path = "/getAppNames")
	public ResponseEntity<?> getAppNames(@RequestBody TicketTeam ticketTeam) {
		System.out.println("hello");
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		List<TicketApp> ticketAppNameList = new ArrayList<TicketApp>();
		logger.info("name" + ticketTeam.getTeamId());

		ticketAppNameList = ticket.getAppNames(ticketTeam);
		if (ticketAppNameList.size() > 0) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage("ticketAppNameList is found");
			responseBody.setResult(ticketAppNameList);
		} else {
			responseBody.setReqStatus("failed");
			responseBody.setMessage("ticketAppNameList is not found");
		}

		return ResponseEntity.ok(responseBody);

	}

	@PostMapping(path = "/getEnvTypes")
	public ResponseEntity<?> getEnvTypes(@RequestBody TicketEnvResTeamApp ticketEnvResTeamApp) {
		System.out.println("hello");
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		List<TicketEnvType> ticketEnvTypeList = new ArrayList<TicketEnvType>();
		logger.info("name" + ticketEnvResTeamApp.getTeamId());

		ticketEnvTypeList = ticket.getEnvTypes(ticketEnvResTeamApp);
		if (ticketEnvTypeList.size() > 0) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage("ticketAppNameList is found");
			responseBody.setResult(ticketEnvTypeList);
		} else {
			responseBody.setReqStatus("failed");
			responseBody.setMessage("ticketAppNameList is not found");
		}

		return ResponseEntity.ok(responseBody);

	}

	@PostMapping(path = "/getResNames")
	public ResponseEntity<?> getResNames(@RequestBody TicketEnvResTeamApp ticketEnvResTeamApp) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		List<TicketResource> ticketEnvTypeList = new ArrayList<TicketResource>();

		ticketEnvTypeList = ticket.getResNames(ticketEnvResTeamApp);
		if (ticketEnvTypeList.size() > 0) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage("ticketAppNameList is found");
			responseBody.setResult(ticketEnvTypeList);
		} else {
			responseBody.setReqStatus("failed");
			responseBody.setMessage("ticketAppNameList is not found");
		}

		return ResponseEntity.ok(responseBody);

	}

	@PostMapping(path = "/" + "#")
	public ResponseEntity<?> getResCredentials(@RequestBody TicketResource ticketResource) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		List<TicketResCredentials> TicketResCredentialsList = new ArrayList<TicketResCredentials>();

		TicketResCredentialsList = ticket.getResCredentials(ticketResource);
		if (TicketResCredentialsList.size() > 0) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage("ticketAppNameList is found");
			responseBody.setResult(TicketResCredentialsList);
		} else {
			responseBody.setReqStatus("failed");
			responseBody.setMessage("ticketAppNameList is not found");
		}

		return ResponseEntity.ok(responseBody);

	}

	// TickectResourceSubType
	/*
	 * @PostMapping(path = "/saveTicketResSubType") public ResponseEntity<?>
	 * saveTicketResSubType(@RequestBody TicketResSubType ticketResSubType) {
	 * ResponseBodyHelper responseBody = new ResponseBodyHelper();
	 * logger.info("List of TicketResSubTypeName.." + ticketResSubType);
	 * ticket.createTicketResSubType(ticketResSubType);
	 * responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
	 * responseBody.setReqStatus("success");
	 * responseBody.setMessage(errorcodes.getERRORS_NEWAPPLICATIONCREATION_SUCCESS()
	 * ); return ResponseEntity.ok(responseBody); }
	 * 
	 * @GetMapping(path = "/getTicketResSubTypeList") public ResponseEntity<?>
	 * getTicketResSubTypeList(){ ResponseBodyHelper responseBody = new
	 * ResponseBodyHelper(); List<TicketResSubType> ticketResSubTypeList = new
	 * ArrayList<TicketResSubType>(); ticketResSubTypeList =
	 * ticket.getTicketResSubTypeList(); if (ticketResSubTypeList.size() > 0) {
	 * responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
	 * responseBody.setReqStatus("success");
	 * responseBody.setMessage("TicketResourceTypeList is found");
	 * responseBody.setResult(ticketResSubTypeList); } else {
	 * responseBody.setReqStatus("failed");
	 * responseBody.setMessage("TicketResourceTypeList is not found"); }
	 * 
	 * return ResponseEntity.ok(responseBody);
	 * 
	 * }
	 */
	@PostMapping(path = "/saveUser")
	public ResponseEntity<?> createTicketUser(@RequestBody TicketUser ticketUser) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		logger.info("List of TicketResSubTypeName.." + ticketUser);
		System.out.println(ticketUser.getUserName());
		ticket.createTicketUser(ticketUser);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("user created successfully");

		return ResponseEntity.ok(responseBody);

	}

	@GetMapping(path = "/getUserList")
	public ResponseEntity<?> getTicketUserList() {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		List<TicketUser> userList = new ArrayList<TicketUser>();
		userList = ticket.getTicketUserList();
		logger.info("displaying ticketApplication List ...." + userList);
		if (userList.size() > 0) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage("TicketAppList is found");
			responseBody.setResult(userList);
		} else {
			responseBody.setReqStatus("failed");
			responseBody.setMessage("TicketAppList is not found");
		}

		return ResponseEntity.ok(responseBody);

	}

	@GetMapping(path = "/getListOfOptionTypes")
	public ResponseEntity<?> getListOfOptionTypes() {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		List<OptionType> OptionTypeList = new ArrayList<OptionType>();
		OptionTypeList = ticket.getListOfOptionTypes();
		logger.info("displaying ticketApplication List ...." + OptionTypeList);
		if (OptionTypeList.size() > 0) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage("ListOfOptionTypes is found");
			responseBody.setResult(OptionTypeList);
		} else {
			responseBody.setReqStatus("failed");
			responseBody.setMessage("ListOfOptionTypes is not found");
		}

		return ResponseEntity.ok(responseBody);

	}

	@PostMapping(path = "/saveRole")
	public ResponseEntity<?> createTicketRole(@RequestBody TicketRole ticketRole) {

		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		ticket.createTicketRole(ticketRole);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("role is  created successfully");
		return ResponseEntity.ok(responseBody);

	}

	@PostMapping(path = "/updatedOptionTypes")
	public ResponseEntity<?> getSeletedOptionTypes(@RequestBody TicketRole ticketRole) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		List<OptionType> list = ticket.getSeletedOptionTypes(ticketRole);
		// List unassignList=ticketRole.getDeletedTypeIds();
		List<Integer> InsertOpsIds = ticketRole.getOptionTypeIds();
		List<Integer> deletedOpsIds = new ArrayList();
		List<Integer> updatelist = new ArrayList();
		int id1 = 0;
		int id2 = 0;

		List remove = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			int id = list.get(i).getOpTypeId();
			deletedOpsIds.add(id);
		}
		for (int i = 0; i < deletedOpsIds.size(); i++) {
			id2 = deletedOpsIds.get(i);
			for (int j = 0; j < InsertOpsIds.size(); j++) {
				id1 = InsertOpsIds.get(j);
				if (id2 == id1) {
					remove.add(id2);
				}
			}
		}

		deletedOpsIds.removeAll(remove);
		InsertOpsIds.removeAll(remove);
		ticketRole.setDeletedTypeIds(deletedOpsIds);
		ticketRole.setOptionTypeIds(InsertOpsIds);
		// System.out.println("insertlist......"+insertlist);
		// System.out.println("assignList......"+assignList);
		ticket.UpdateRoleTypes(ticketRole);

		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("updated");
		//responseBody.setResult(list);
		return ResponseEntity.ok(responseBody);
	}
	@GetMapping(path = "/getListRoles")
	public ResponseEntity<?> getListRoles() {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		List<TicketRole> rolesList = new ArrayList<TicketRole>();
		rolesList = ticket.getListRoles();
		logger.info("displaying ticketApplication List ...." + rolesList);
		if (rolesList.size() > 0) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage("ListOfOptionTypes is found");
			responseBody.setResult(rolesList);
		} else {
			responseBody.setReqStatus("failed");
			responseBody.setMessage("ListOfOptionTypes is not found");
		}

		return ResponseEntity.ok(responseBody);

	}
	
	@PostMapping(path = "/showUpdateRole")
	public ResponseEntity<?> showUpdateRoles(@RequestBody TicketRole ticketRole) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		List<OptionType>OptionTypeLists = ticket.getListOfOptionTypes();
		List<OptionType> assignList = ticket.getSeletedOptionTypes(ticketRole);
		ArrayList<Integer> listOfUnAssignIds=new ArrayList<Integer>();
		ArrayList<Integer> listOfAssignIds=new ArrayList<Integer>();
		ArrayList<OptionType> unAssinList=new ArrayList<OptionType>();
		int unassinId;
		int assinId;
		for(int i=0;i<OptionTypeLists.size();i++) {
			unassinId=OptionTypeLists.get(i).getOpTypeId();
			listOfUnAssignIds.add(unassinId);
		}
		for(int i=0;i<assignList.size();i++) {
			assinId=assignList.get(i).getOpTypeId();
			listOfAssignIds.add(assinId);
		}
	
		listOfUnAssignIds.removeAll(listOfAssignIds);
		for(int i=0;i < listOfUnAssignIds.size() ;i++) {
			int value=listOfUnAssignIds.get(i);
			for(int j=0;j < OptionTypeLists.size() ;j++) {
				if(value==OptionTypeLists.get(j).getOpTypeId()) {
					unAssinList.add(OptionTypeLists.get(j));
				}
			}
		}
		ticketRole.setAssign(assignList);
		ticketRole.setUnassign(unAssinList);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("ListOfOptionTypes is found");
		responseBody.setResult(ticketRole);
	
	return ResponseEntity.ok(responseBody);
}

		@PostMapping(path = "/deleteRole")
		public ResponseEntity<?> deletedRole(@RequestBody TicketRole ticketRole) {
			ResponseBodyHelper responseBody = new ResponseBodyHelper();
			ticket.deletedRole(ticketRole);
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage("role is deleted");
			return ResponseEntity.ok(responseBody);

		}
	
	}
	