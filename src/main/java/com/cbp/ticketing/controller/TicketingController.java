package com.cbp.ticketing.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbp.ticketing.action.implService.TicketingServiceImpl;
import com.cbp.ticketing.model.ResponseBodyHelper;
import com.cbp.ticketing.model.TicketApp;
import com.cbp.ticketing.model.TicketEnvResTeamApp;
import com.cbp.ticketing.model.TicketEnvType;
import com.cbp.ticketing.model.TicketResCredentials;
import com.cbp.ticketing.model.TicketResource;
import com.cbp.ticketing.model.TicketResourceType;
import com.cbp.ticketing.model.TicketTeam;
import com.cbp.ticketing.model.UserLogin;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/ticketing")
public class TicketingController {
	private static final Logger logger = LoggerFactory.getLogger(TicketingController.class);
	@Value("${errors.lookup.unknown}")
	private String unknown;

	@Autowired
	TicketingServiceImpl ticket;

	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody UserLogin user) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		logger.info("Login User Name" + user.getUserName());
		List<UserLogin> users = ticket.login(user);
		if (users.size() > 0) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage("user is login successfuly");
			responseBody.setResult(users);

		} else {
			responseBody.setStatusCode(String.valueOf(HttpStatus.NON_AUTHORITATIVE_INFORMATION));
			responseBody.setReqStatus("failed");
			responseBody.setMessage(unknown);
		}
		return ResponseEntity.ok(responseBody);

	}

//TicketApp Controller
	@PostMapping(path = "/saveTicketApp")
	public ResponseEntity<?> saveTicketApp(@RequestBody TicketApp ticketApp) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		/*
		 * logger.info("AppName Name" + ticketApp.getAppName());
		 */ List names = ticketApp.getAppNames();
		logger.info("List of TicketAppNames.." + names);
		for (int i = 0; i < names.size(); i++) {
			TicketApp TicketApps = new TicketApp();
			String name = names.get(i).toString();
			System.out.println("name...." + name);
			TicketApps.setAppName(name);
			ticket.createTicketApp(TicketApps);
		}
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("Record(s) Saved");
		return ResponseEntity.ok(responseBody);
	}

	@GetMapping(path = "/getTicketAppList")
	public ResponseEntity<ResponseBodyHelper> getTicketAppList() {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		List<TicketApp> ticketAppList = new ArrayList<TicketApp>();
		ticketAppList = ticket.getTicketAppList();
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
			responseBody.setMessage("updated successfuly");
		} else {
			responseBody.setReqStatus("failed");
			responseBody.setMessage("updated failed");
		}
		return ResponseEntity.ok(responseBody);
	}

	@PostMapping(path = "/deleteTicketApp")
	public ResponseEntity<?> deleteTicketApp(@RequestBody TicketApp ticketApp) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		ticket.deleteTicketApp(ticketApp);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("AppTicket is deleted");
		return ResponseEntity.ok(responseBody);
	}

//TicketTeam Controllers
	@PostMapping(path = "/saveTicketTeam")
	public ResponseEntity<?> saveTicketTeam(@RequestBody TicketTeam ticketTeam) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		ticket.createTicketTeam(ticketTeam);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("TicketTeam Record Saved");
		return ResponseEntity.ok(responseBody);
	}

	@GetMapping(path = "/getTicketTeamList")
	public ResponseEntity<?> getTicketTeamList() {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		List<TicketTeam> ticketAppList = new ArrayList<TicketTeam>();
		ticketAppList = ticket.getTicketTeamList();
		if (ticketAppList.size() > 0) {
			responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
			responseBody.setReqStatus("success");
			responseBody.setMessage("TicketTeamList is found");
			responseBody.setResult(ticketAppList);
		} else {
			responseBody.setReqStatus("failed");
			responseBody.setMessage("TicketTeamList is not found");
		}

		return ResponseEntity.ok(responseBody);

	}

	@PostMapping(path = "/updateTicketTeam")
	public ResponseEntity<?> updateTicketTeam(@RequestBody TicketTeam ticketTeam) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		System.out.println(ticketTeam.getTeamName());
		ticket.updateTicketTeam(ticketTeam);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("updated successfuly");
		return ResponseEntity.ok(responseBody);
	}

	@PostMapping(path = "/deleteTicketTeam")
	public ResponseEntity<?> deleteTicketTeam(@RequestBody TicketTeam ticketTeam) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		ticket.deleteTicketTeam(ticketTeam);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("TicketTeamis deleted");
		return ResponseEntity.ok(responseBody);

	}

	// TicketRes Controllers
	@PostMapping(path = "/saveTicketRes")
	public ResponseEntity<?> saveTicketRes(@RequestBody TicketResource ticketResource) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();

		ticket.createTicketRes(ticketResource);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("TicketRes Record Saved");
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
		responseBody.setMessage("updated successfuly");
		return ResponseEntity.ok(responseBody);
	}

	@PostMapping(path = "/deleteTicketResource")
	public ResponseEntity<?> deleteTicketResource(@RequestBody TicketResource ticketRes) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		ticket.deleteTicketResource(ticketRes);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("TicketResource is deleted");
		return ResponseEntity.ok(responseBody);

	}

	// TicketEnvType Controllers

	@PostMapping(path = "/saveTicketEnvType")
	public ResponseEntity<?> createTicketEnvType(@RequestBody TicketEnvType ticketEnvType) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();

		ticket.createTicketEnvType(ticketEnvType);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("TicketEnvType Record Saved");
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
		responseBody.setMessage("updated successfuly");
		return ResponseEntity.ok(responseBody);
	}

	@PostMapping(path = "/deleteTicketEnvType")
	public ResponseEntity<?> deleteTicketEnvType(@RequestBody TicketEnvType ticketEnvType) {
		ResponseBodyHelper responseBody = new ResponseBodyHelper();
		ticket.deleteTicketEnvType(ticketEnvType);
		responseBody.setStatusCode(String.valueOf(HttpStatus.OK));
		responseBody.setReqStatus("success");
		responseBody.setMessage("TicketEnvType is deleted");
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
		System.out.println(ticketResType.getResTypeName());
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

	@PostMapping(path = "/getResCredentials")
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

}