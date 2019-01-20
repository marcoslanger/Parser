package com.log.parser.pojo;


/**
 * Class to store the command line parameters.
 * 
 * @author Marcos
 *
 */
public class ParametersPojo {

	private String accesslog = null;
	private String[] startDate = null;
	private String duration = null;
	private int threshold = 0;

	/**
	 * @return the accesslog
	 */
	public String getAccesslog() {
		return accesslog;
	}

	/**
	 * @param accesslog
	 *            the accesslog to set
	 */
	public void setAccesslog(String accesslog) {
		this.accesslog = accesslog;
	}

	/**
	 * @return the startDate
	 */
	public String[] getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String[] startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the threshold
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * @param threshold
	 *            the threshold to set
	 */
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

}
