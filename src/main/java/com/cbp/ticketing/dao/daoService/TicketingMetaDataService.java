package com.cbp.ticketing.dao.daoService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;

import com.cbp.ticketing.model.ServicenowData;
import com.cbp.ticketing.model.TableMetadata;

public interface TicketingMetaDataService {
	public List<TableMetadata> getDevDatabaseTablesMetadata(TableMetadata metadata) throws SQLException;
	 public List<TableMetadata> getListOfUsedTableMetadata(String sqlquery) ;
	 public List<TableMetadata> getDevListOfMatchedMetadata(String sqlquery) throws SQLException ;
	 public boolean userPermission(String schema, String statement) throws IOException, JSONException;
	 public ServicenowData validate(String sqlquery, String jsonInput, String dbselection) throws IOException, JSONException;
	 public String executeSqlScript(String incidentNumber, String fullPath, String fileName) throws IOException, JSONException;
}
