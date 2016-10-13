package com.quantil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Generator {
	
	public boolean generate() throws ParseException {
		System.out.println("Generating log data, estimated time: 10 secs...");
		
		long start = System.currentTimeMillis();
		
		Properties prop = PropertyUtil.getProperty();
		
		String date = prop.getProperty("log.date");
		String logOutputPath = prop.getProperty("log.output.path");
		int serverCount = Integer.parseInt(prop.getProperty("number.of.servers", "1000"));
		int cpuPerServer = Integer.parseInt(prop.getProperty("number.of.cpu.per.server", "1000"));
		int flushPer = Integer.parseInt(prop.getProperty("flush.record.per", "1000"));
		
		List<String> ipList = populateServerList(serverCount);
		
		if(ipList == null) {
			System.out.println("IP address out of range, unable to generate log data!");
			return false;
		}
		
		StringBuilder logOutput = new StringBuilder();
		FileUtil.writeToFile(logOutputPath, "timestamp\tIP\tcpu_id\tusage\n", false);
		
		int logEntry = 0;
		
		for (int hour = 0; hour <= 23; hour++) {
		    for (int minute = 0; minute <= 59; minute++) {
		    	for(String ip : ipList) {
		    		for(int cpu = 0; cpu < cpuPerServer; cpu++) {
				    	long unixTime = getUnixTime(date, hour, minute, 0);
				    	Random rand = new Random();
				    	int usage = rand.nextInt(101);
				    	getLogEntry(logOutput, ip, cpu, unixTime, usage);
				    	logEntry++;
				    	
//				    	if(logEntry > flushPer) {
//				    		FileUtil.writeToFile(logOutputPath, logOutput.toString(), true);
//				    		logOutput.setLength(0);
//				    		logEntry = 0;
//				    	}
		    		}
		    	}
		    }
		}
		
		FileUtil.writeToFile(logOutputPath, logOutput.toString(), true);
		logOutput.setLength(0);
		
		long end = System.currentTimeMillis();
		
		System.out.println(String.format("Total time (sec): %d", ((end - start) / 1000)));
		
		return true;
	}

	private void getLogEntry(StringBuilder logOutput, String ip, int cpu, long unixTime, int usage) {
		logOutput.append(unixTime);
		logOutput.append("\t");
		logOutput.append(ip);
		logOutput.append("\t");
		logOutput.append(cpu);
		logOutput.append("\t");
		logOutput.append(usage);
		logOutput.append("\n");
	}

	private long getUnixTime(String date, int hour, int minute, int sec) throws ParseException {
		StringBuilder sb = new StringBuilder();
		sb.append(date);
		sb.append(" ");
		sb.append(hour);
		sb.append(":");
		sb.append(minute);
		sb.append(":");
		sb.append(sec);
		
		return Utility.getUnixTime(sb.toString(), "yyyy-MM-dd HH:mm:ss");
	}
	
	private List<String> populateServerList(int serverCount) {
		List<String> ipList = new ArrayList<>();
		
		for(int i = 0; i < 256; i++) {
			for(int j = 0; j < 256; j++) {
				if(serverCount <= 0) {
					return ipList;
				}
				ipList.add("192.168." + i  + "."+ j);
				serverCount--;
			}
		}
		
		return null;
	}
	
}
