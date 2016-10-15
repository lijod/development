package com.quantil;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Utility {
	
	private static Properties prop = PropertyUtil.getProperty();
	
	public static long getUnixTime(String time, String format) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);
		Date startDate = df.parse(time);
		long unixTime = startDate.getTime() / 1000L;
		return unixTime;
	}
	
	public static String getLogDate() {
		return prop.getProperty("log.date");
	}
	
	public static String getLogPath() {
		return prop.getProperty("app.dir") + File.pathSeparator + prop.getProperty("log.file.name");
	}
	
	public static int getServerCount() {
		return Integer.parseInt(prop.getProperty("number.of.servers", "1000"));
	}
	
	public static int getCPUCount() {
		return Integer.parseInt(prop.getProperty("number.of.cpu.per.server", "2"));
	}
	
	public static int getFlushPerCount() {
		return Integer.parseInt(prop.getProperty("flush.record.per"))	;
	}
}
