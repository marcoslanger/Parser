package com.log.parser.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.log.parser.pojo.ParametersPojo;
import com.log.parser.util.MessagesUtils;
import com.log.parser.util.PaserUtils;


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

			params = PaserUtils.fillArguments(args);			
			br = new BufferedReader(new FileReader(params.getAccesslog()));
			ipList = new ArrayList<String>();

			String currentLine;

			// scans the rows of the log file entered in the command
			// line by looking for the specified criteria.

			while ((currentLine = br.readLine()) != null) {

				splitedStr = currentLine.split(TOKEN);

				String[] splitedDateTime = splitedStr[DATETIME].split("\\ ");

				if (params.getDuration().equals(DURATION_TYPE[PaserUtils.ZERO])
						&& splitedDateTime[PaserUtils.ZERO].equals(params.getStartDate()[PaserUtils.ZERO])) {
					
					//ip found by daily criteria.
					
					ipList.add(splitedStr[IP]);

				} else if (params.getDuration().equals(DURATION_TYPE[PaserUtils.ONE])
						&& (splitedDateTime[PaserUtils.ZERO].equals(params.getStartDate()[PaserUtils.ZERO])
								&& splitedDateTime[PaserUtils.ONE].split("\\:")[PaserUtils.ZERO]
										.equals(params.getStartDate()[PaserUtils.ONE].split("\\:")[PaserUtils.ZERO]))) {
					
					//ip found by the hourly criteria.
					
					ipList.add(splitedStr[IP]);

				}
			}

			// calls the persistence method by passing a list of log
			// entities found in the query specified in the command line.

			persistLog(PaserUtils.fillLogEntities(ipList, params));

		} catch (FileNotFoundException e) {
			System.out.println(MessagesUtils.FILE_NOT_FOUND + params.getAccesslog());
		} catch (IOException ex) {
			System.out.println(MessagesUtils.READ_ERROR);
		} finally {

			try {

				if (br != null) {
					br.close();
				}				

			} catch (IOException ex) {
				System.out.println(MessagesUtils.READ_ERROR);
			}
		}
	}

}
