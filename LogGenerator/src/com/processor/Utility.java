package com.processor;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Utility class hold the static utility method. This keeps common logic centralized 
 * @author lijodaniel
 *
 */
public class Utility {
	
	private static Properties prop = PropertyUtil.getProperty();
	
	/**
	 * Returns a unix time stamp corresponding to input date string
	 * @param time Date-time in string format
	 * @param format Format of the date
	 * @return Unix time stamp corresponding to input date string 
	 * @throws ParseException
	 */
	public static long getUnixTime(String time, String format) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);
		Date startDate = df.parse(time);
		long unixTime = startDate.getTime() / 1000L;
		return unixTime;
	}
	
	/**
	 * 
	 * @return Date for logging
	 */
	public static String getLogDate() {
		return prop.getProperty("log.date");
	}
	
	/**
	 * 
	 * @return Path for log file from properties
	 */
	public static String getLogPath() {
		return prop.getProperty("app.dir") + File.separator + prop.getProperty("log.file.name");
	}
	
	/**
	 * 
	 * @return Path for catalog file from properties
	 */
	public static String getCatalogPath() {
		return prop.getProperty("app.dir") + File.separator + prop.getProperty("catalog.file.name");
	}
	
	/**
	 * 
	 * @return Count of server from properties
	 */
	public static int getServerCount() {
		return Integer.parseInt(prop.getProperty("number.of.servers"));
	}
	
	/**
	 * 
	 * @returnCount of CPU from properties
	 */
	public static int getCPUCount() {
		return Integer.parseInt(prop.getProperty("number.of.cpu.per.server"));
	}
	
	/**
	 * 
	 * @return Number of record to be written per batch when writing log files
	 */
	public static int getFlushPerCount() {
		return Integer.parseInt(prop.getProperty("flush.record.per"));
	}

	/**
	 * Sets the app.dor in application properties
	 * @param appDir Local directory in which application files are processed
	 */
	public static void setAppDir(String appDir) {
		prop.setProperty("app.dir", appDir);
	}
}
