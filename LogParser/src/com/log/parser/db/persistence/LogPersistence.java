package com.log.parser.db.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.log.parser.db.entity.LogEntity;
import com.log.parser.help.DBHelp;


/**
 * Class that controls the persistence of the log in the database.
 * 
 * @author Marcos
 *
 */
public class LogPersistence {

	private static final String INSERT_LOG = "insert into parserdb.parser_log (ip, requests, comment, period) values (?, ?, ?, ?)";

	/**
	 * Includes a new log entity in the database.
	 * 
	 * @param log a new entity to be included.
	 * 
	 * @return an int to control whether inclusion was successful.
	 */
	public int incluirLogEntity(LogEntity log) {

		PreparedStatement ps = null;
		int dbreturn = 0;

		try {
			ps = DBHelp.getConexaoMySQL().prepareStatement(INSERT_LOG);
			ps.setString(1, log.getIp());
			ps.setInt(2, log.getRequests().intValue());
			ps.setString(3, log.getComment());
			ps.setString(4, log.getPeriod());

			dbreturn = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dbreturn;
	}

}
