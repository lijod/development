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
		return prop.getProperty("app.dir") + File.separator + prop.getProperty("log.file.name");
	}
	
	public static String getCatalogPath() {
		return prop.getProperty("app.dir") + File.separator + prop.getProperty("catalog.file.name");
	}
	
	public static int getServerCount() {
		return Integer.parseInt(prop.getProperty("number.of.servers"));
	}
	
	public static int getCPUCount() {
		return Integer.parseInt(prop.getProperty("number.of.cpu.per.server"));
	}
	
	public static int getFlushPerCount() {
		return Integer.parseInt(prop.getProperty("flush.record.per"))	;
	}

	public static void setAppDir(String appDir) {
		prop.setProperty("app.dir", appDir);
	}
}
