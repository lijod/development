package com.quantil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generator class generates a log entry each minute for a given day. The log is generated 
 * for each CPU for each server. 
 * Server range: 192.168.0.0 - 192.168.255.255
 * CPU range: >=0
 * Time range: 00:00 - 23:59 
 * @author lijodaniel
 */
public class Generator {
	
	public Generator(String appDir) {
		Utility.setAppDir(appDir);
	}
	
	/**
	 * Call this method to generate the log file.
	 * @return true if log is generated successfully
	 * @throws ParseException
	 */
	public boolean generate() throws ParseException {
		System.out.println("Generating log data, estimated time: 10 secs...");
		
		long start = System.currentTimeMillis();
		
		String date = Utility.getLogDate();
		String logOutputPath = Utility.getLogPath();
		int serverCount = Utility.getServerCount();
		int cpuPerServer = Utility.getCPUCount();
		int flushPer = Utility.getFlushPerCount();
		
		List<String> ipList = populateServerList(serverCount);
		if(ipList == null) {
			System.out.println("IP address out of range, unable to generate log data!");
			return false;
		}
		
		generateLogForDate(date, ipList, cpuPerServer, logOutputPath, flushPer);
		
		long end = System.currentTimeMillis();
		System.out.println(String.format("Total time (sec): %d", ((end - start) / 1000)));
		
		return true;
	}
	
	/**
	 * generates a log entry each minute for a given day. The log is generated 
	 * for each CPU of each server. 
	 * Server range: 192.168.0.0 - 192.168.255.255
	 * CPU range: >=0
	 * Time range: 00:00 - 23:59
	 * 
	 * @param date Date for which of is to be generated
	 * @param ipList List of server IPs for which log is to be  created
	 * @param cpuPerServer Number of CPU for which log is to be generated per server
	 * @param logOutputPath Log file path
	 * @param flushPer Log to be flush at interval of flushPer records
	 * @throws ParseException
	 */
	private void generateLogForDate(String date, List<String> ipList, int cpuPerServer, String logOutputPath,
			int flushPer) throws ParseException {
		StringBuilder logOutput = new StringBuilder();
		FileUtil.writeToFile(logOutputPath, "timestamp\tIP\tcpu_id\tusage\n", false);
		
		int logEntry = 0;
		
		for (int hour = 0; hour <= 23; hour++) {
		    for (int minute = 0; minute <= 59; minute++) {
		    	for(String ip : ipList) {
		    		for(int cpu = 0; cpu < cpuPerServer; cpu++) {
				    	long unixTime = getUnixTime(date, hour, minute, 0);
				    	Random rand = new Random();
				    	// Generates a random number between 0 - 100 for usage
				    	int usage = rand.nextInt(101);
				    	getLogEntry(logOutput, ip, cpu, unixTime, usage);
				    	logEntry++;
				    	
				    	// Flush data to output file if the processed line count is more 
				    	// than flushPer count
				    	if(logEntry > flushPer) {
				    		FileUtil.writeToFile(logOutputPath, logOutput.toString(), true);
				    		// Sets length of the StringBuilder to 0 to release memory
				    		logOutput.setLength(0);
				    		logEntry = 0;
				    	}
		    		}
		    	}
		    }
		}
		
		// Flush all log data left in memory
		FileUtil.writeToFile(logOutputPath, logOutput.toString(), true);
	}

	/**
	 * Generates a log entry and appends it to StringBuilder logOutput
	 * @param logOutput StringBuilder to be updated
	 * @param ip IP if server
	 * @param cpu CPU id of the server
	 * @param unixTime Timestamp
	 * @param usage Usage percentage of CPU j of Server i at time t
	 */
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

	/**
	 * Returns a UNIX style timestamp for date, hour, minute, and sec specified
	 * @param date 
	 * @param hour
	 * @param minute
	 * @param sec
	 * @return Unix based timestamp
	 * @throws ParseException
	 */
	private long getUnixTime(String date, int hour, int minute, int sec) throws ParseException {
		StringBuilder sb = new StringBuilder();
		
		sb.append(date);
		sb.append(" ");
		sb.append(hour);
		sb.append(":");
		sb.append(minute);
		sb.append(":");
		sb.append(sec);
		
		return Utility.getUnixTime(sb.toString(), AppConstants.DATE_TYPE_yyyyMMddHHmmss);
	}
	
	/**
	 * Returns a list of strings representing IP addresses of server based on the server count 
	 * specified in the input. 
	 * Server range: 192.168.0.0 - 192.168.255.255
	 * @param serverCount
	 * @return List of strings representing IP addresses of server
	 */
	private List<String> populateServerList(int serverCount) {
		List<String> ipList = new ArrayList<>();
		
		for(int i = 0; i < 256; i++) {
			for(int j = 0; j < 256; j++) {
				if(serverCount <= 0) {
					return ipList;
				}
				ipList.add(String.format("192.168.%d.%d", i, j));
				serverCount--;
			}
		}
		
		return null;
	}
	
}
