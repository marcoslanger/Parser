package com.log.parser.help;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.log.parser.db.entity.LogEntity;
import com.log.parser.pojo.ParametersPojo;

/**
 * Class to help in parsing log lines.
 * 
 * @author Marcos
 *
 */
public class PaserHelp {
	
	public static final String CMD_FILE = "--accesslog";
	public static final String CMD_START = "--startDate";
	public static final String CMD_DURATION = "--duration";
	public static final String CMD_THRESHOLD = "--threshold";
	
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int ARGS_SIZE = 4;
	
	
	/**
	 * Parses the command line and populates the arguments.
	 * 
	 * @param args the command line arguments.
	 * 
	 * @return ParametersPojo a pojo with command line parameters.
	 */
	public static ParametersPojo fillArguments(String args[]) {

		if (args.length == ZERO) {
			
			System.out.println(MessagesHelp.NO_ARGS);
			System.exit(ZERO);
			
		} else if (args.length < ARGS_SIZE) {
			
			System.out.println(MessagesHelp.MISS_ARGS);
			System.exit(ZERO);
		}

		int i = ZERO;
		String splitedArgs[] = null;
		ParametersPojo params = new ParametersPojo();
		Map<String, String> arguments = new HashMap<String, String>();

		while (i < args.length && args[i].startsWith("--")) {
			
			if (args[i].contains("=")) {
				
				splitedArgs = args[i++].split("\\=");
				arguments.put(splitedArgs[ZERO], splitedArgs[ONE]);
				
			} else {
				
				System.out.println(MessagesHelp.MALFORMED);
				System.exit(ZERO);
			}
		}

		for (String key : arguments.keySet()) {

			switch (key) {
			case CMD_FILE:
				params.setAccesslog(arguments.get(key));
			}

			switch (key) {
			case CMD_START:
				params.setStartDate(arguments.get(key).split("\\."));
			}

			switch (key) {
			case CMD_DURATION:
				params.setDuration(arguments.get(key));
			}

			switch (key) {
			case CMD_THRESHOLD:
				params.setThreshold(Integer.parseInt(arguments.get(key)));
			}
		}
		return params;
	}
	
	
	/**
	 * Fills the log entities for persistence in the database.
	 * @param ipList list with the ips found by the query.
	 * @param params pojo with the data entered in the command line.
	 * @return list with logging entities to be persisted.
	 */
	public static List<LogEntity> fillLogEntities(List<String> ipList, ParametersPojo params) {
		Map<String, Long> counts = ipList.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

		List<LogEntity> logList = new ArrayList<LogEntity>();

		for (String ip : counts.keySet()) {
			
			if (counts.get(ip) >= params.getThreshold()) {
				
				//prints the found ips to the output.
				System.out.println(MessagesHelp.IPS_FOUND + ip + MessagesHelp.TOTAL_REQ + counts.get(ip));
				
				LogEntity log = new LogEntity();
				log.setIp(ip);
				log.setRequests(counts.get(ip).intValue());
				log.setComment(MessagesHelp.COMMENT);
				log.setPeriod(params.getStartDate()[ZERO] + " " + params.getStartDate()[ONE]);
				logList.add(log);
			}
		}
		return logList;
	}	

}
