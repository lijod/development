package com.quantil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	
	public static long getUnixTime(String time, String format) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);
		Date startDate = df.parse(time);
		long unixTime = startDate.getTime() / 1000L;
		return unixTime;
	}
	
}
