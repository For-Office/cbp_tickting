package com.cbp.ticketing.model;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by rajeevkumarsingh on 21/11/17.
 */

public class TableMetadata  {
	String Database; 
	 
	String Schema;	
	List<TableStructure> TableStructure=new ArrayList<TableStructure>();
	String tableName;
	String statement;
	String HostNumber;
	/*Set<String> existingcolumns=new HashSet<String>();*/
	
	 


public String getHostNumber() {
		return HostNumber;
	}

	public void setHostNumber(String hostNumber) {
		HostNumber = hostNumber;
	}

/*public Set<String> getExistingcolumns() {
		return existingcolumns;
	}

	public void setExistingcolumns(Set<String> existingcolumns) {
		this.existingcolumns = existingcolumns;
	}
*/
public String getDatabase() {
		return Database;
	}

	public String getStatement() {
	return statement;
}

public void setStatement(String statement) {
	this.statement = statement;
}

	public void setDatabase(String database) {
		Database = database;
	}

public String getSchema() {
	return Schema;
}

public void setSchema(String schema) {
	Schema = schema;
}

public List<TableStructure> getTableStructure() {
	return TableStructure;
}

public void setTableStructure(List<TableStructure> tableStructure) {
	TableStructure = tableStructure;
}

public String getTableName() {
	return tableName;
}

public void setTableName(String tableName) {
	this.tableName = tableName;
}

    
    

   
    }
