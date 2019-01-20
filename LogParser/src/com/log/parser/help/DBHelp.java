package com.log.parser.help;

import java.io.IOException;
import java.io.InputStream;
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
public class DBHelp {
	
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
			prop = loadProperties();
			Class.forName(MYSQL_DRIVER);			
			String url = prop.getProperty(CONNECTION_STR) + prop.getProperty(SERVER_NAME) + "/" + prop.getProperty(DATABASE);	
			connection = DriverManager.getConnection(url, prop.getProperty(USERNAME), prop.getProperty(PASSWORD));

			return connection;

		} catch (ClassNotFoundException e) {
			System.out.println(MessagesHelp.NOT_FOUND);
			return null;

		} catch (SQLException e) {
			System.out.println(MessagesHelp.SQL_ERROR);
			return null;
		}
	}
	
	private static Properties loadProperties() {
		
		Properties prop = new Properties();
    	InputStream input = null;    	
    	
    	try {        
    		
    		input = DBHelp.class.getClassLoader().getResourceAsStream(APP_PROPS);
    		
    		if(input == null) {
    	            System.out.println("Sorry, unable to find " + APP_PROPS);
    		}

    		//load a properties file from class path, inside static method
    		prop.load(input);    		
 
    	} catch (IOException ex) {
    		System.out.println(MessagesHelp.READ_ERROR);
        } finally {
        	if (input != null) {
        		try {
				input.close();
				} catch (IOException e) {
					System.out.println(MessagesHelp.READ_ERROR);
				}
        	}
        }
    	return prop;
	}

}
