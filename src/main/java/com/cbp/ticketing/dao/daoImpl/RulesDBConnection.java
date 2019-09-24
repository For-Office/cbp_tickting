package com.cbp.ticketing.dao.daoImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * @author Mahesh Ganti
 * @version 1.0 $Date: 28/01/2019
 * 
 *
 */
@Repository
public class RulesDBConnection {

	@Value("${db.postgre.DB_URL}")
	private String DB_URL;
	
	@Value("${db.postgre.DB_USER}")
	private  String DB_USER;
	
	@Value("${db.postgre.DB_PASSWORD}")
	private  String DB_PASSWORD;
	
	@Value("${db1.postgre.QA_DB_URL}")
	private String QA_DB_URL;
	
	@Value("${db1.postgre.QA_DB_USER}")
	private  String QA_DB_USER;
	
	@Value("${db1.postgre.QA_DB_PASSWORD}")
	private  String QA_DB_PASSWORD;
	
	@Value("${db2.mysql.MY_DB_URL}")
	private String MY_DB_URL;
	
	@Value("${db2.mysql.MY_DB_USER}")
	private  String MY_DB_USER;
	
	@Value("${db2.mysql.MY_DB_PASSWORD}")
	private  String MY_DB_PASSWORD;
	
	public  Connection getDevDatabaseConection(String dbname) throws SQLException {
		Connection connection =null;
		if (dbname.equals("spring"))
		{
		 connection = DriverManager.getConnection(DB_URL, DB_USER,DB_PASSWORD);
		}
		else if(dbname.equals("db1"))
		{
			System.err.println("The connection is successfully obtaineddddddd");
			 connection = DriverManager.getConnection(QA_DB_URL, QA_DB_USER,QA_DB_PASSWORD);
		}
		else if(dbname.equals("mysql1"))
		{
			 connection = DriverManager.getConnection(MY_DB_URL, MY_DB_USER,MY_DB_PASSWORD);
		}
	//	System.err.println("The connection is successfully obtained");
		return connection;
	}
	
	
	/*private static String DB_URL = "jdbc:mysql://localhost:3307/test";
	private static String DB_USER = "admin";
	private static String DB_PASSWORD = "admin";*/
	/*private static final String DB_URL = "jdbc:postgresql://localhost/VELogInfo"; 
    private static final String DB_USER = "postgres";     
    private static final String DB_PASSWORD = "password";
*/
	
	/*public  Connection getQAdatabaseConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(QA_DB_URL, QA_DB_USER,QA_DB_PASSWORD);
		System.err.println("The connection is successfully obtained");
		return connection;
	}*/
}