package com.cbp.ticketing.action.implService;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbp.ticketing.dao.daoImpl.TicketingMetaDataImpl;
import com.cbp.ticketing.model.ServicenowData;
import com.cbp.ticketing.model.TableMetadata;


@Service
public class TicketingMetaDataServiceImpl {
	@Autowired
    private TicketingMetaDataImpl ticketingMetaDataImpl; 
	
	 public List<TableMetadata> getDevDatabaseTablesMetadata(TableMetadata metadata) throws SQLException {
	        return ticketingMetaDataImpl.getDevDatabaseTablesMetadata(metadata);
	    }
	 public List<TableMetadata> getListOfUsedTableMetadata(String sqlquery) {
		 return ticketingMetaDataImpl.getListOfUsedTableMetadata(sqlquery);
	 }
	 public List<TableMetadata> getDevListOfMatchedMetadata(String sqlquery) throws SQLException {
	 return ticketingMetaDataImpl.getDevListOfMatchedMetadata(sqlquery);
	 }
	 public boolean userPermission(String schema, String statement) throws IOException, JSONException {
	 return ticketingMetaDataImpl.userPermission(schema,statement);
	 }
	 public ServicenowData validate(String sqlquery, String jsonInput, String dbselection) throws IOException, JSONException{
		 return ticketingMetaDataImpl.validate(sqlquery,jsonInput,dbselection);
	 }
	 
	 
}
