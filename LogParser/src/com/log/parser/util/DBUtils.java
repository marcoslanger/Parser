package com.log.parser.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Utility class for database connection.
 * 
 * @author Marcos
 *
 */
public class DBUtils {
	
	private static final String MYSQL_DRIVER = "mysql_driver";
	private static final String SERVER_NAME = "server_name";
	private static final String DATABASE = "database";
	private static final String CONNECTION_STR = "connection_str";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";	
	
	private static final String APP_PROPS = "resources/application.properties";
	
	private static Properties prop = null;
	
	
	/**
	 * Get a database conecction.
	 * 
	 * @return conecction a connection.
	 */
	public static Connection getConexaoMySQL() {
		
		
		Connection connection = null;

		try {	
			prop = PropsUtils.loadProperties(APP_PROPS);
			Class.forName(prop.getProperty(MYSQL_DRIVER));			
			String url = prop.getProperty(CONNECTION_STR) + prop.getProperty(SERVER_NAME) + "/" + prop.getProperty(DATABASE);	
			connection = DriverManager.getConnection(url, prop.getProperty(USERNAME), prop.getProperty(PASSWORD));

			return connection;

		} catch (ClassNotFoundException e) {
			System.out.println(MessagesUtils.NOT_FOUND);
			return null;

		} catch (SQLException e) {
			System.out.println(MessagesUtils.SQL_ERROR);
			return null;
		}
	}
	

}
