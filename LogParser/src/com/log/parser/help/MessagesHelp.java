package com.log.parser.help;


/**
 * Class to store application messages.
 * 
 * @author Marcos
 *
 */
public class MessagesHelp {
	
	//db messages
	public static final String NOT_FOUND = "Specified driver not found.";
	public static final String SQL_ERROR = "An error occurred while querying the database.";

	//parser messages
	public static final String COMMENT = "Blocked by excessive requests during the consultation period.";
	public static final String NO_ARGS = "No argument was passed to the application.";
	public static final String MISS_ARGS = "Number of arguments smaller than expected.";
	public static final String MALFORMED = "One or more arguments are malformed.";
	
	//user messages
	public static final String IPS_FOUND = "IP(s) found by the query: ";
	public static final String TOTAL_REQ = " with the total requests: ";
	
	//io messages
	public static final String FILE_NOT_FOUND  = "File not found or unable to open file: "; 
	public static final String READ_ERROR = "Error reading file.";
	
}
