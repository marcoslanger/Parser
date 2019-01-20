package com.log.parser.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for load properties.
 * 
 * @author Marcos
 *
 */
public class PropsUtils {	
	

/**
 * Method for load a properties file.
 * @return
 */
public static Properties loadProperties(String filename) {
		
		Properties prop = new Properties();
    	InputStream input = null;    	
    	
    	try {        
    		
    		input = DBUtils.class.getClassLoader().getResourceAsStream(filename);
    		
    		if(input == null) {
    	            System.out.println("Sorry, unable to find " + filename);
    		}

    		//load a properties file from class path, inside static method
    		prop.load(input);    		
 
    	} catch (IOException ex) {
    		System.out.println(MessagesUtils.READ_ERROR);
        } finally {
        	if (input != null) {
        		try {
				input.close();
				} catch (IOException e) {
					System.out.println(MessagesUtils.READ_ERROR);
				}
        	}
        }
    	return prop;
	}

}
