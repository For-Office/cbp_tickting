package com.cbp.ticketing.controller;

import java.io.IOException;
import java.util.List;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cbp.ticketing.action.implService.ServiceNowImplSevice;
import com.cbp.ticketing.model.DownloadAttachement;
import com.cbp.ticketing.model.ServicenowData;

@RestController
public class ServiceNowController {
	@Autowired
    private ServiceNowImplSevice serviceNowImplSevice;
	 @PostMapping("/servicenow/updateincident/{incidentNumber}")    
	    public  ServicenowData ServicenowUpdate(@PathVariable(value = "incidentNumber") String incidentNumber,@RequestBody(required = false) String jsonRequest) throws IOException, JSONException {
	    
	        return serviceNowImplSevice.ServicenowUpdate(incidentNumber,jsonRequest);

	}
	 @PostMapping("/servicenow/createincident")    
	    public  ServicenowData ServicenowUpdate(@RequestBody(required = false) String jsonRequest) throws IOException, JSONException {
	    
	        return serviceNowImplSevice.ServicenowCreate(jsonRequest);

	}
	 @GetMapping("/servicenow/incidentdetails/{incidentNumber}")    
	    public  ServicenowData servicenow(@PathVariable(value = "incidentNumber") String incidentNumber) throws IOException, JSONException {
	    	 return serviceNowImplSevice.getServicenowDetails(incidentNumber);
	}
	 @GetMapping("/servicenow/attachement/{incidentNumber}")    
	    public List<DownloadAttachement> DownloadfileServicenow(@PathVariable(value = "incidentNumber") String incidentNumber) throws IOException, JSONException {
	    	 return serviceNowImplSevice.DownloadfileServicenow(incidentNumber);
	}
	 @GetMapping("/servicenow/executeSqlScript/{incidentNumber}")    
	    public String executeSqlScript(@PathVariable(value = "incidentNumber") String incidentNumber) throws IOException, JSONException {
	     return serviceNowImplSevice.executeSqlScript(incidentNumber,null,null);
	 }
	 @GetMapping("/servicenow/uploadattachement/{incidentNumber}")    
	    public List<String> UploadfileServicenow(@PathVariable(value = "incidentNumber") String incidentNumber) throws IOException, JSONException {
	    	return serviceNowImplSevice.UploadFileServicenow(incidentNumber);
}
}
