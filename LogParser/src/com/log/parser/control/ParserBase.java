package com.log.parser.control;

import java.util.List;

import com.log.parser.db.entity.LogEntity;
import com.log.parser.db.persistence.LogPersistence;

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
			dbreturn = new LogPersistence().incluirLogEntity(logEntity);
		}
		return dbreturn;
	}

	

}
