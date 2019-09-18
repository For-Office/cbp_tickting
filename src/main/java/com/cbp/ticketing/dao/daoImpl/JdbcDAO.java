package com.cbp.ticketing.dao.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcDAO {
	@Value("${db.postgre.DB_URL}")
	private String DB_URL;
	@Value("${db.postgre.DB_USER}")
	private String DB_USER;
	@Value("${db.postgre.DB_PASSWORD}")
	private String DB_PASSWORD;

	public Connection getDevDatabaseConection() throws SQLException {
		Connection connection = null;
		connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		System.err.println("The connection is successfully obtained");
		return connection;
	}
	
	
	protected int getMaxId(Connection connection, String columnName, String tableName) throws SQLException {
		int maxId = 0;
		StringBuilder MAX_ID_QUERY = new StringBuilder();
		MAX_ID_QUERY.append("SELECT max(");
		MAX_ID_QUERY.append(columnName);
		MAX_ID_QUERY.append(") as max_id FROM ");
		MAX_ID_QUERY.append(tableName);

		PreparedStatement maxIdStatement = null;
		ResultSet maxIdResultSet = null;

		try {
			maxIdStatement = connection.prepareStatement(MAX_ID_QUERY.toString());
			maxIdResultSet = maxIdStatement.executeQuery();

			if (maxIdResultSet != null && maxIdResultSet.next()) {
				maxId = maxIdResultSet.getInt("max_id");
			}

		} finally {
			maxIdResultSet.close();
			maxIdStatement.close();
		}
		return maxId;
	}
	
}
/*else if(dbname.equals("postgre2"))
{
	 connection = DriverManager.getConnection(QA_DB_URL, QA_DB_USER,QA_DB_PASSWORD);
}
else if(dbname.equals("mysql1"))
{
	 connection = DriverManager.getConnection(MY_DB_URL, MY_DB_USER,MY_DB_PASSWORD);
}*/
