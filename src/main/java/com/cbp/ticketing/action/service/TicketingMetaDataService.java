package com.cbp.ticketing.action.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.cbp.ticketing.model.TableMetadata;


 interface TicketingMetaDataService {

	 public List<TableMetadata> getDevDatabaseTablesMetadata(TableMetadata metadata) throws SQLException ;
	 public List<TableMetadata> getListOfUsedTableMetadata(String sqlquery) ;
	 public List<TableMetadata> getDevListOfMatchedMetadata(String sqlquery) throws SQLException ;
	 public boolean userPermission(String schema, String statement) throws IOException, JSONException;
    
}