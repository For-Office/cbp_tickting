package com.cbp.ticketing.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbp.ticketing.action.implService.TicketingMetaDataServiceImpl;
import com.cbp.ticketing.model.TableMetadata;

@RestController
@EnableAutoConfiguration
@RequestMapping(path = "/metadata")
public class TicketingMetaDataController {

	  @Autowired
	    private TicketingMetaDataServiceImpl ticketingService;
	    
	   
//	    @GetMapping("/getDevDatabaseTablesMetadata")
//	    public List<TableMetadata> getDevDatabaseTablesMetadata() throws SQLException {
//	        return ticketingService.getDevDatabaseTablesMetadata();
//	    }
	    
	    @PostMapping(value="/getDevDatabaseTablesMetadata",produces=MediaType.APPLICATION_JSON_VALUE)
	    public List<TableMetadata> getDevDatabaseTablesMetadata1(@RequestBody TableMetadata metadata) throws SQLException {
	        return ticketingService.getDevDatabaseTablesMetadata(metadata);
	    }
	    @PostMapping("/valid")
	    public List<TableMetadata> getListOfUsedTableMetadata(@RequestBody(required = false) String sqlquery) throws SQLException {
	    	//sqlquery="select a.ENV_TYPE_ID,a.ENV_TYPE_NAME,B.ENV_TYPE_ID,B.ENV_TYPE_NAME from TICKETING.CBP_TKT_ENV_TYPE a left join TICKETING_META.CBP_TKT_ENV_TYPE b on a.ENV_TYPE_ID=b.ENV_TYPE_ID";
	        System.out.println(sqlquery);
	    	return ticketingService.getListOfUsedTableMetadata(sqlquery);
	    }
	    @PostMapping("/getDevListOfMatchedMetadata")
	    public List<TableMetadata> getDevListOfMatchedMetadata(@RequestBody(required = false) String sqlquery) throws SQLException {
	        return ticketingService.getDevListOfMatchedMetadata(sqlquery);
	    }
	   /* @PostMapping("/validate/{env}")
	    public ServicenowData validate(@RequestBody(required = false) String sqlquery,@PathVariable(value = "env") String env) throws SQLException, IOException, JSONException {
	         return rulesRepository.validate(sqlquery,null,env);
	    }*/
	    @GetMapping("/usertest/{schema}/{statement}")
	    public boolean userpermission(@PathVariable(value = "schema") String schema, @PathVariable(value = "statement") String statement) throws SQLException, IOException, JSONException {
	         return ticketingService.userPermission(schema,statement);
	    }
	    
	}
