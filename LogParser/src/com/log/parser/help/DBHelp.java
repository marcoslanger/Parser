package com.log.parser.help;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Utility class for database connection.
 * 
 * @author Marcos
 *
 */
public class DBHelp {
	
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String SERVER_NAME = "localhost";
	public static final String DATABASE = "mysql";
	public static final String CONNECTION_STR = "jdbc:mysql://";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "adm";
	
	
	/**
	 * Get a database conecction.
	 * 
	 * @return conecction a connection.
	 */
	public static Connection getConexaoMySQL() {

		Connection connection = null;

		try {			
			Class.forName(MYSQL_DRIVER);			
			String url = CONNECTION_STR + SERVER_NAME + "/" + DATABASE;	
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);

			return connection;

		} catch (ClassNotFoundException e) {
			System.out.println(MessagesHelp.NOT_FOUND);
			return null;

		} catch (SQLException e) {
			System.out.println(MessagesHelp.SQL_ERROR);
			return null;
		}
	}

}
