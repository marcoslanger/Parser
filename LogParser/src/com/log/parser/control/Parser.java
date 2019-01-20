package com.log.parser.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.log.parser.help.MessagesHelp;
import com.log.parser.help.PaserHelp;
import com.log.parser.pojo.ParametersPojo;

/**
 * Class responsible for parsing a log file and finding information
 * according to criteria passed by the command line.
 * 
 * @author Marcos
 *
 */
public class Parser extends ParserBase {

	/**
	 * Main method of the application.
	 * 
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {

		BufferedReader br = null;		
		String[] splitedStr = null;
		List<String> ipList = null;
		ParametersPojo params = null;

		try {

			params = PaserHelp.fillArguments(args);			
			br = new BufferedReader(new FileReader(params.getAccesslog()));
			ipList = new ArrayList<String>();

			String currentLine;

			// scans the rows of the log file entered in the command
			// line by looking for the specified criteria.

			while ((currentLine = br.readLine()) != null) {

				splitedStr = currentLine.split(TOKEN);

				String[] splitedDateTime = splitedStr[DATETIME].split("\\ ");

				if (params.getDuration().equals(DURATION_TYPE[PaserHelp.ZERO])
						&& splitedDateTime[PaserHelp.ZERO].equals(params.getStartDate()[PaserHelp.ZERO])) {
					
					//ip found by daily criteria.
					
					ipList.add(splitedStr[IP]);

				} else if (params.getDuration().equals(DURATION_TYPE[PaserHelp.ONE])
						&& (splitedDateTime[PaserHelp.ZERO].equals(params.getStartDate()[PaserHelp.ZERO])
								&& splitedDateTime[PaserHelp.ONE].split("\\:")[PaserHelp.ZERO]
										.equals(params.getStartDate()[PaserHelp.ONE].split("\\:")[PaserHelp.ZERO]))) {
					
					//ip found by the hourly criteria.
					
					ipList.add(splitedStr[IP]);

				}
			}

			// calls the persistence method by passing a list of log
			// entities found in the query specified in the command line.

			persistLog(PaserHelp.fillLogEntities(ipList, params));

		} catch (FileNotFoundException e) {
			System.out.println(MessagesHelp.FILE_NOT_FOUND + params.getAccesslog());
		} catch (IOException ex) {
			System.out.println(MessagesHelp.READ_ERROR);
		} finally {

			try {

				if (br != null) {
					br.close();
				}				

			} catch (IOException ex) {
				System.out.println(MessagesHelp.READ_ERROR);
			}
		}
	}

}
