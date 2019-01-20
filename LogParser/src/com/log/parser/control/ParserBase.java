package com.log.parser.control;

import java.util.List;

import com.log.parser.domain.dao.LogDAO;
import com.log.parser.domain.entity.LogEntity;


/**
 * Base class of implementation.
 * 
 * @author Marcos
 *
 */
public class ParserBase {

	public static final String TOKEN = "\\|";
	public static final int DATETIME = 0;
	public static final int IP = 1;
	public static final String[] DURATION_TYPE = { "daily", "hourly" };
	
		

	/**
	 * Performs the inclusion of the query in the log in the database.
	 * 
	 * @param logList a list of entities with the data to be included.
	 * 
	 * @return an int to control whether inclusion was successful.
	 */
	public static int persistLog(List<LogEntity> logList) {
		int dbreturn = 0;

		for (LogEntity logEntity : logList) {
			dbreturn = new LogDAO().incluirLogEntity(logEntity);
		}
		return dbreturn;
	}

	

}
