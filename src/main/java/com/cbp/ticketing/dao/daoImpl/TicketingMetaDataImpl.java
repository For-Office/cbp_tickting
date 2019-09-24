package com.cbp.ticketing.dao.daoImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cbp.ticketing.action.implService.WhereConditionTraverser;
import com.cbp.ticketing.controller.TicketingController;
import com.cbp.ticketing.dao.daoService.TicketingMetaDataService;
import com.cbp.ticketing.model.DeleteMetadata;
import com.cbp.ticketing.model.SelectMetadata;
import com.cbp.ticketing.model.TableMetadata;
import com.cbp.ticketing.model.TableStructure;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.EExpressionType;
import gudusoft.gsqlparser.TCustomSqlStatement;
import gudusoft.gsqlparser.TGSqlParser;
import gudusoft.gsqlparser.nodes.TInsertCondition;
import gudusoft.gsqlparser.nodes.TInsertIntoValue;
import gudusoft.gsqlparser.nodes.TMultiTarget;
import gudusoft.gsqlparser.nodes.TResultColumn;
import gudusoft.gsqlparser.stmt.TDeleteSqlStatement;
import gudusoft.gsqlparser.stmt.TInsertSqlStatement;
import gudusoft.gsqlparser.stmt.TSelectSqlStatement;

@Repository
public class TicketingMetaDataImpl implements TicketingMetaDataService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	RulesDBConnection rulesDBConnection;
	Connection connection = null;
	DatabaseMetaData metadata = null;
	Connection qaconnection = null;
	DatabaseMetaData qametadata = null;
	Connection mysqlconnection = null;
	DatabaseMetaData mysqlmetadata = null;
	
	@PostConstruct
	public void initialize() {
		try {
			connection = rulesDBConnection.getDevDatabaseConection("db1");

		} catch (SQLException e) {
			System.err.println("There was an error getting the connection: " + e.getMessage());
		}

	}
	private static final Logger logger = LoggerFactory.getLogger(TicketingController.class);
	
	/*public DatabaseMetaData getDevDatabaseMetaData() throws SQLException {
		 metadata = jdbcTemplate.getDataSource().getConnection().getMetaData();
		 System.out.println("metadata"+metadata);
	return metadata;
}
	*/
	/*public List<TableMetadata> getColumnsMetadata(ArrayList<String> tables, String schema, String db,DatabaseMetaData metadata1 )
			throws SQLException {
		List<TableMetadata> tableMetadataList = new ArrayList<TableMetadata>();
		ResultSet rs = null;
		// Print the columns properties of the actual table
		for (String actualTable : tables) {
			String[] actualTable1 = actualTable.split("@");
			TableMetadata tableMetadata = new TableMetadata();
			tableMetadata.setDatabase(db);
			tableMetadata.setSchema(actualTable1[0]);
			tableMetadata.setTableName(actualTable1[1].toUpperCase());
			//System.out.println("Schame Name::"+actualTable1[0]);
			List<TableStructure> tableStructureList = new ArrayList<TableStructure>();
			rs = metadata1.getColumns(null, actualTable1[0], actualTable1[1], null);
		
			while (rs.next()) {

				TableStructure a = new TableStructure();
				a.setColumn_name(rs.getString("COLUMN_NAME"));
				a.setDatatype(rs.getString("TYPE_NAME"));
				a.setSize(rs.getInt("COLUMN_SIZE"));
				a.setPrecision(rs.getInt("DECIMAL_DIGITS"));

				tableStructureList.add(a);
			}
			tableMetadata.setTableStructure(tableStructureList);
			tableMetadataList.add(tableMetadata);
		}
		return tableMetadataList;
	}*/
	public List<TableMetadata> getDevDatabaseTablesMetadata(TableMetadata tmetadata) throws SQLException {
		getDevDatabaseMetaData();
		String table[] = { "TABLE" };
		ResultSet rs = null;
		String schemaName=tmetadata.getSchema();
		System.out.println("schemaName "+schemaName);
		ArrayList<String> tables = null;
		// receive the Type of the object in a String array.
		rs = metadata.getTables(null, schemaName.toUpperCase(), "%", table);
		tables = new ArrayList<String>();
		String schema = null;
		while (rs.next()) {
			schema = rs.getString("TABLE_SCHEM");
		//	if(schema.equalsIgnoreCase("undraelu_ds")) {
				tables.add(schema + "@" + rs.getString("TABLE_NAME"));
			//}
				System.out.println("Schema " +rs.getString("TABLE_SCHEM"));

		}
		
		
		return getColumnsMetadata(tables, null, metadata.getDatabaseProductName(),metadata);
	}
	 public List<TableMetadata> getListOfUsedTableMetadata(String sqlquery) {

			List<TableMetadata> tables = new ArrayList<TableMetadata>();
			TGSqlParser sqlparser = new TGSqlParser(EDbVendor.dbvpostgresql);
			System.out.println("testting");
			// sqlparser.sqltext = "select a.id, a.state,a.ve_serial_no,b.state1 from
			// public.veinfo a left join velog.veinfo b on a.id=b.id";
			sqlparser.sqltext = sqlquery;
			int ret = sqlparser.parse();
			if (ret != 0) {
				System.err.println(sqlparser.getErrormessage());
				System.out.println("testting111111111");
			} else {
				System.out.println("testttttttttttt" + sqlparser.sqlstatements.size());
				for (int k = 0; k < sqlparser.sqlstatements.size(); k++) {
					if (sqlparser.sqlstatements.get(k) instanceof TCustomSqlStatement) {
						tables = analyzeStatement((TCustomSqlStatement) sqlparser.sqlstatements.get(k),
								sqlparser.getDbVendor().name());
					}
				}
			}
			return tables;
		}

	 public List<TableMetadata> analyzeStatement(TCustomSqlStatement stmt, String db) {
			List<TableMetadata> tableMetadataList = new ArrayList<TableMetadata>();
			SelectMetadata selectMetadata = new SelectMetadata();
			DeleteMetadata deleteMetadata = new DeleteMetadata();
			if (stmt instanceof TSelectSqlStatement) {
				Set<String> columnslist = selectMetadata.getListOfResultColumns((TSelectSqlStatement) stmt);
				Set<String> tables = selectMetadata.analyzeSelectStatement((TSelectSqlStatement) stmt);
				if (!tables.isEmpty()) {

					String[] tableArray = tables.toArray(new String[0]);
					for (int i = 0; i < tableArray.length; i++) {
						TableMetadata a = new TableMetadata();
						a.setDatabase(db);
						a.setTableName(tableArray[i].toUpperCase());
						if (tableArray[i].contains(".")) {
							String[] tableArray1 = tableArray[i].split("\\.");
							a.setDatabase(db);
							a.setSchema(tableArray1[0]);
							a.setTableName(tableArray1[1].toUpperCase());
							a.setStatement("select");

						}
						List<TableStructure> tableStructureList = new ArrayList<TableStructure>();

						for (String columnslist1 : columnslist) {
							TableStructure a1 = new TableStructure();
							a1.setColumn_name(columnslist1);

							tableStructureList.add(a1);
						}
						a.setTableStructure(tableStructureList);
						System.out.print(tableArray[i]);

						tableMetadataList.add(a);

					}

				}
			} else if (stmt instanceof TInsertSqlStatement) {
				System.out.println("insert");
				TInsertSqlStatement insert = (TInsertSqlStatement) stmt;
				Set<String> tables = analyzeInsertStatement(insert);
				if (!tables.isEmpty()) {

					String[] tableArray = tables.toArray(new String[0]);
					for (int i = 0; i < tableArray.length; i++) {
						TableMetadata a = new TableMetadata();

						if (tableArray[i].contains(".")) {
							String[] tableArray1 = tableArray[i].split("\\.");
							a.setDatabase(db);
							a.setSchema(tableArray1[0]);
							a.setTableName(tableArray1[1].toUpperCase());

						}
						List<TableStructure> tableStructureList = new ArrayList<TableStructure>();

						/*
						 * for (String columnslist1:columnslist) { TableStructure a1=new
						 * TableStructure(); a1.setColumn_name(columnslist1);
						 * 
						 * tableStructureList.add(a1); }
						 */
						a.setTableStructure(tableStructureList);
						System.out.print(tableArray[i]);

						tableMetadataList.add(a);

					}
				}
			} else if (stmt instanceof TDeleteSqlStatement) {
				TDeleteSqlStatement delete = (TDeleteSqlStatement) stmt;
				WhereConditionTraverser traverser = new WhereConditionTraverser();
				List<String> columnlist = traverser.getListOfValuesInWhereCondition(stmt);

				Set<String> tables = deleteMetadata.analyzeDeleteStatement(delete, stmt);
				if (!tables.isEmpty()) {

					String[] tableArray = tables.toArray(new String[0]);

					for (int i = 0; i < tableArray.length; i++) {
						TableMetadata a = new TableMetadata();
						a.setDatabase(db);
						a.setTableName(tableArray[i].toUpperCase());
						if (tableArray[i].contains(".")) {
							String[] tableArray1 = tableArray[i].split("\\.");
							a.setDatabase(db);
							a.setSchema(tableArray1[0]);
							a.setTableName(tableArray1[1].toUpperCase());

						}

						List<TableStructure> tableStructureList = new ArrayList<TableStructure>();
						for (String columnname : columnlist) {

							TableStructure a1 = new TableStructure();
							a1.setColumn_name(columnname);
							System.out.println("columnArray[k]" + columnname);
							tableStructureList.add(a1);
						}
						a.setTableStructure(tableStructureList);

						/*
						 * for (String columnslist1:columnslist) {
						 * 
						 * }
						 */

						System.out.print(tableArray[i]);

						tableMetadataList.add(a);

					}
				}

			}

			/*
			 * else if ( stmt instanceof TUpdateSqlStatement ) { TUpdateSqlStatement update
			 * = (TUpdateSqlStatement) stmt; analyzeUpdateStatement( update ); }
			 * 
			 * else if ( stmt instanceof TDropTableSqlStatement ) { TDropTableSqlStatement
			 * drop = (TDropTableSqlStatement) stmt; if ( drop.getTableName( ) != null ) {
			 * System.out.println( "Target: " + drop.getTableName( ).toString( ) ); } } else
			 * if ( stmt instanceof TAlterTableStatement ) { TAlterTableStatement alter =
			 * (TAlterTableStatement) stmt; if ( alter.getTableName( ) != null ) {
			 * System.out.println( "Target: " + alter.getTableName( ).toString( ) ); } }
			 * else if ( stmt instanceof TMergeSqlStatement ) { TMergeSqlStatement merge =
			 * (TMergeSqlStatement) stmt; analyzeMergeStatement( merge ); }
			 */

			return tableMetadataList;
		}
	 private Set<String> analyzeInsertStatement(TInsertSqlStatement insert) {
			Set<String> targets = new LinkedHashSet<String>();
			SelectMetadata selectMetadata = new SelectMetadata();
			if (insert.getTargetTable() != null) {
				if (insert.getTargetTable() != null) {
					if (insert.getTargetTable().isBaseTable() && !targets.contains(insert.getTargetTable().getFullName()))
						targets.add(insert.getTargetTable().getFullName());
					else if (insert.getTargetTable().getSubquery() != null) {
						targets.addAll(selectMetadata.analyzeSelectStatement(insert.getTargetTable().getSubquery()));
					}
				}
			}
			if (insert.getInsertIntoValues() != null) {
				for (int i = 0; i < insert.getInsertIntoValues().size(); i++) {
					TInsertIntoValue intoValue = insert.getInsertIntoValues().getElement(i);
					if (intoValue.getTable() != null && intoValue.getTable().isBaseTable()
							&& !targets.contains(intoValue.getTable().getFullName()))
						targets.add(intoValue.getTable().getFullName());
				}

			}
			if (insert.getInsertConditions() != null) {
				for (int i = 0; i < insert.getInsertConditions().size(); i++) {
					TInsertCondition intoCondition = insert.getInsertConditions().getElement(i);
					if (intoCondition.getInsertIntoValues() != null) {
						for (int j = 0; j < intoCondition.getInsertIntoValues().size(); j++) {
							TInsertIntoValue intoValue = intoCondition.getInsertIntoValues().getElement(j);
							if (intoValue.getTable() != null && intoValue.getTable().isBaseTable()
									&& !targets.contains(intoValue.getTable().getFullName()))
								targets.add(intoValue.getTable().getFullName());
						}
					}
				}
			}

			Set<String> sources = new LinkedHashSet<String>();

			if (insert.getValues() != null) {
				for (int i = 0; i < insert.getValues().size(); i++) {
					TMultiTarget multiTarget = insert.getValues().getMultiTarget(i);
					if (multiTarget.getSubQuery() != null) {
						sources.addAll(selectMetadata.analyzeSelectStatement(multiTarget.getSubQuery()));
					}

					for (int j = 0; j < multiTarget.getColumnList().size(); j++) {
						TResultColumn field = multiTarget.getColumnList().getResultColumn(j);
						System.out.println("field" + field);
						System.out.println("field1" + multiTarget);
						System.out.println("field2" + field.getTargetColumns().getColumnNo());
						if (field.getExpr().getExpressionType() == EExpressionType.subquery_t) {
							sources.addAll(selectMetadata.analyzeSelectStatement(field.getExpr().getSubQuery()));
						}
					}
				}
			}

			if (insert.getSubQuery() != null) {
				sources.addAll(selectMetadata.analyzeSelectStatement(insert.getSubQuery()));
			}

			Set<String> tables = new HashSet<String>();
			tables.addAll(targets);
			// tables.addAll(sources);
			return tables;
		}
	 public List<TableMetadata> getDevListOfMatchedMetadata(String sqlquery) throws SQLException {
			List<TableMetadata> tables = new ArrayList<TableMetadata>();

			List<TableMetadata> getListOfUsedTableMetadata = getListOfUsedTableMetadata(sqlquery);

			for (TableMetadata getListOfUsedTableMetadata1 : getListOfUsedTableMetadata) {
				System.out.println("getListOfUsedTableMetadata1.getTableName()::::::::" + getListOfUsedTableMetadata1.getTableName());
				List<TableMetadata> getSchemaOneTablesMetadata = getDevDatabaseTablesMetadata(getListOfUsedTableMetadata1.getTableName());
				for (TableMetadata getSchemaOneTablesMetadata1 : getSchemaOneTablesMetadata) {
					if (getSchemaOneTablesMetadata1.getSchema().equalsIgnoreCase(getListOfUsedTableMetadata1.getSchema())
							&& getSchemaOneTablesMetadata1.getTableName()
									.equalsIgnoreCase(getListOfUsedTableMetadata1.getTableName())) {
						
						TableMetadata tableMetadata = new TableMetadata();
						// Set<String> existing=new HashSet<String>();
						// tableMetadata.setExistigcolumns(getSchemaOneTablesMetadata1.getTableStructure());
						System.out.println("2" + getListOfUsedTableMetadata1.getSchema() + ":"
								+ getListOfUsedTableMetadata1.getTableName());
						tableMetadata.setSchema(getSchemaOneTablesMetadata1.getSchema());
						tableMetadata.setDatabase(getSchemaOneTablesMetadata1.getDatabase());
						tableMetadata.setTableName(getSchemaOneTablesMetadata1.getTableName());
						tableMetadata.setStatement(getListOfUsedTableMetadata1.getStatement());
						List<TableStructure> tableStructureList = new ArrayList<TableStructure>();
						for (TableStructure a : getListOfUsedTableMetadata1.getTableStructure()) {
							for (TableStructure b : getSchemaOneTablesMetadata1.getTableStructure()) {

								System.out.println("table columns::::" + b.getColumn_name());
								if (a.getColumn_name().equalsIgnoreCase(b.getColumn_name())) {
									TableStructure tableStructure = new TableStructure();

									// System.out.println("fffffffffffhhhhhhhhhhh"+getListOfUsedTableMetadata1.getSchema()+":"+getListOfUsedTableMetadata1.getTableName());
									// System.out.println(a.getColumn_name());
									tableStructure.setColumn_name(b.getColumn_name());
									tableStructure.setDatatype(b.getDatatype());
									tableStructure.setSize(b.getSize());
									tableStructure.setPrecision(b.getPrecision());

									tableStructureList.add(tableStructure);
								}
								// existing.add(b.getColumn_name());
							}
						}
						tableMetadata.setTableStructure(tableStructureList);
						// tableMetadata.setDbcolumns(existing);
						tables.add(tableMetadata);
					}

				}

			}
			return tables;
		}
	 public List<TableMetadata> getDevDatabaseTablesMetadata(String tableName) throws SQLException {
			getDevDatabaseMetaData();
			// String table[] = { "notes" };
			System.out.println("table:::::::::::"+tableName);
			ResultSet rs = null;
			ArrayList<String> tables = null;
			// receive the Type of the object in a String array.
			rs = metadata.getTables(null, "%", tableName, null);
			tables = new ArrayList<String>();
			String schema = null;
			while (rs.next()) {
				schema = rs.getString("TABLE_SCHEM");
				tables.add(schema + "@" + rs.getString("TABLE_NAME"));
				System.out.println("table:::::::::::"+schema + "@" + rs.getString("TABLE_NAME"));

			}
			return getColumnsMetadata(tables, null, metadata.getDatabaseProductName(),metadata);
		}
	 public DatabaseMetaData getDevDatabaseMetaData() throws SQLException {

			try {
				metadata = connection.getMetaData();
			} catch (SQLException e) {
				System.err.println("There was an error getting the metadata: " + e.getMessage());
			}
			System.out.println("testing:::::::::::::::::::::::::::::::::::::::::;"+metadata);

			return metadata;

		}
	 public List<TableMetadata> getColumnsMetadata(ArrayList<String> tables, String schema, String db,DatabaseMetaData metadata1 )
				throws SQLException {
			List<TableMetadata> tableMetadataList = new ArrayList<TableMetadata>();
			ResultSet rs = null;
			// Print the columns properties of the actual table
			for (String actualTable : tables) {
				String[] actualTable1 = actualTable.split("@");
				TableMetadata tableMetadata = new TableMetadata();
				tableMetadata.setDatabase(db);
				tableMetadata.setSchema(actualTable1[0]);
				tableMetadata.setTableName(actualTable1[1].toUpperCase());
				System.out.println("test" + actualTable1[0]);
				System.out.println("test1" + actualTable1[1]);
				List<TableStructure> tableStructureList = new ArrayList<TableStructure>();
				rs = metadata1.getColumns(null, actualTable1[0], actualTable1[1], null);

				System.out.println(actualTable.toUpperCase());

				while (rs.next()) {

					TableStructure a = new TableStructure();
					a.setColumn_name(rs.getString("COLUMN_NAME"));
					a.setDatatype(rs.getString("TYPE_NAME"));
					a.setSize(rs.getInt("COLUMN_SIZE"));
					a.setPrecision(rs.getInt("DECIMAL_DIGITS"));

					tableStructureList.add(a);
				}
				tableMetadata.setTableStructure(tableStructureList);
				tableMetadataList.add(tableMetadata);
			}
			return tableMetadataList;
		}
	 public boolean userPermission(String schema, String statement) throws IOException, JSONException {
			String devserver=null;
			String devdbname=null;
			String devSchema=null;
			System.out.println("teststatmenet:::::"+statement);
			boolean flag=false;
			try {
				
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM public.user_permission");
				
				
				while (rs.next()) {
						  
						String [] devdbarr=rs.getString(2).split("@");
						System.out.println("testing"+rs.getString(2));
						 devserver=devdbarr[0];
						 devdbname=devdbarr[1];
						 devSchema=devdbarr[2];
						 System.out.println("testing"+devserver+"::"+devdbname+"::"+devSchema+"::::::::::::"+statement.toLowerCase()+"::"+schema); 
						 if (schema.equals(devSchema)) {
							 if (rs.getString(3).toLowerCase().contains(statement.toLowerCase()))
									 {
							 System.out.println("testing11111::"+rs.getString(3));
							 flag=true;
									 }
						 }
					}
				
			
		} catch (SQLException e) {
			System.err.println("There was an error getting the connection: " + e.getMessage());
		}
			return flag;
		}
}
