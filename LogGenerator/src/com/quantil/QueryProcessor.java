package com.quantil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class QueryProcessor {
	
	private final Map<Long, Long> timeOffsetInFile;
	
	public QueryProcessor() {
		timeOffsetInFile = new HashMap<>();
		try {
			loadTimeOffsetInFile();
		} catch (IOException e) { 
			System.out.println("Unable to load log file for quering!");
			e.printStackTrace();
		}
		System.out.println("Ready to query:");
		process();
	}
	
	private void loadTimeOffsetInFile() throws IOException {
		System.out.println("Loading log file for query processing...");
		
		long start = System.currentTimeMillis();
		
		Properties prop = PropertyUtil.getProperty();
		String logPath = prop.getProperty("log.output.path");
		
		try (RandomAccessFile file = new RandomAccessFile(logPath, "r")) {
			// Skipping the header line
			file.readLine();
			
			String line;
			long prevTime = 0L;
			long lineOffset = file.getFilePointer();
			
			while((line = file.readLine()) != null) {
				String[] lineSplit = line.split("\t");
				if(lineSplit.length != 4) {
					continue;
				}
				
				long currTime = Long.parseLong(lineSplit[0]);
				
				if(prevTime != currTime) {
					timeOffsetInFile.put(currTime, lineOffset);
				}
				
				lineOffset = file.getFilePointer();
			}	
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println(String.format("Total time (sec): %d", ((end - start) / 1000)));
	}

	public void process() {
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			while(true) {
				System.out.println("Please enter one:");
				String input = br.readLine();
				long start = System.currentTimeMillis();
				if(input.equals("EXIT")) {
					System.out.println("Exitting QueryProcessor!");
					return;
				} else if (input.startsWith("QUERY")) {
					System.out.println("Querying...");
					String[] inputSplit = input.split(" +");
				
					if(inputSplit.length != 7) {
						System.out.println("Invalid input for query, please try again...");
						continue;
					}
					
					long startTime = Utility.getUnixTime(inputSplit[3] + " " + inputSplit[4], "yyyy-MM-dd HH:mm");
					long endTime = Utility.getUnixTime(inputSplit[5] + " " + inputSplit[6], "yyyy-MM-dd HH:mm");
					
					System.out.println(getUsage(inputSplit[1], inputSplit[2], startTime, endTime));
					
					long end = System.currentTimeMillis();
					
					System.out.println(String.format("Total query time (ms): %d", (end - start)));
				} else {
					System.out.println("Input invalid!");
					continue;
				}
			}
			
		} catch (ParseException e) {
			System.out.println("Time format invalid, please check you input...");
		} catch (IOException e) {
			System.out.println("Unable to load log file while quering!");
		} catch (Exception e) {
			System.out.println("Unknown Error!");
		}
		
	}
	
	public String getUsage(String server, String cpuId, long startTime, long endTime) throws FileNotFoundException, IOException {
		
//		System.out.println("Start time: " + startTime);
//		System.out.println("End time: " + endTime);
		Properties prop = PropertyUtil.getProperty();
		String logPath = prop.getProperty("log.output.path");
		
		StringBuilder sb = new StringBuilder();
		
		while(startTime <= endTime) {
			long offset = timeOffsetInFile.get(startTime);
//			System.out.println("Start time: " + startTime);
//			System.out.println("Offset: " + offset);
			try (RandomAccessFile file = new RandomAccessFile(logPath, "r")) {
				
				file.seek(offset);
				
				String line;
				while((line = file.readLine()) != null) {
					String[] lineSplit = line.split("\t");
					if(lineSplit.length != 4) {
						continue;
					}
					
					if(lineSplit[1].equals(server) && lineSplit[2].equals(cpuId)) {
						sb.append("(" + startTime + " " + lineSplit[3]  + ")");
						break;
					}
					
				}
				startTime += 60;
			}
		}
		
		return sb.toString();
	}
	
}
