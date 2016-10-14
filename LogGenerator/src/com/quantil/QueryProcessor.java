package com.quantil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class QueryProcessor {
	
	private final Map<String, Long> serverOffsetCatalog;
	private final Properties prop;
	
	public QueryProcessor() {
		serverOffsetCatalog = new HashMap<>();
		prop = PropertyUtil.getProperty();
		
		try {
			generateServerCatalog();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		process();
	}
	
	private void generateServerCatalog() throws FileNotFoundException, IOException {
		System.out.println("Loading log file for query processing...");
		
		long start = System.currentTimeMillis();
		int uniqueTimeCount = 0;
		HashMap<String, List<long[]>> serverTimeOffset = new LinkedHashMap<>();
		
		String logPath = prop.getProperty("log.output.path");
		
		try (RandomAccessFile file = new RandomAccessFile(logPath, "r")) {
			// Skipping the header line
			file.readLine();
			
			String line;
			long prevTime = 0L;
			long lineOffset = file.getFilePointer();
			String prevServerId = "";
			while((line = file.readLine()) != null) {
				
				String[] lineSplit = line.split("\t");
				if(lineSplit.length != 4) {
					continue;
				}
				
				String serverId = lineSplit[1];
				
				// Only saves offset for 1st ip occurrence
				if(prevServerId.equals(serverId)) {
					continue;
				}
				
				long currTime = Long.parseLong(lineSplit[0]);
				
				if(prevTime != currTime) {
					uniqueTimeCount++;
					prevTime = currTime;
					if(uniqueTimeCount == 100) {
						writeIndexToFile(serverTimeOffset);
						serverTimeOffset = new LinkedHashMap<>();
						uniqueTimeCount = 0;
					}
				}
				
				
				if(!serverTimeOffset.containsKey(serverId)) {
					serverTimeOffset.put(serverId, new ArrayList<long[]>());
				}
				
				List<long[]> list = serverTimeOffset.get(serverId);
				
				list.add(new long[]{currTime, lineOffset});
				
				prevServerId = serverId;
				lineOffset = file.getFilePointer();
				
			}
			writeIndexToFile(serverTimeOffset);
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println(String.format("Total time (sec): %d", ((end - start) / 1000)));
	}
	
	public void process() {
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			while(true) {
				try {
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
				
				} catch (ParseException e) {
					System.out.println("Time format invalid, please check you input...");
				} catch (IOException e) {
					System.out.println("Unable to load log file while quering!");
				} catch (Exception e) {
					System.out.println("Unknown Error!");
				}
			}
			
		} catch (IOException e) {
			System.out.println("Unable to load log file while quering!");
		} 
		
	}
	
	public String getUsage(String serverId, String cpuId, long startTime, long endTime) throws FileNotFoundException, IOException {

		String logPath = prop.getProperty("log.output.path");
		String indexPath = prop.getProperty("index.output.path");
		String indexFile = prop.getProperty("index.output.filename");
		String catalogFilePath = indexPath + File.separator + indexFile;
		
		StringBuilder sb = new StringBuilder();
		
		try (RandomAccessFile logFile = new RandomAccessFile(logPath, "r");
			RandomAccessFile catalogFile = new RandomAccessFile(catalogFilePath, "r")) {
			catalogFile.seek(serverOffsetCatalog.get(serverId));
			String serverInfo = catalogFile.readLine();
			String[] catalog = serverInfo.split(" ");
			int catalogIndex = 1;
			while(startTime <= endTime) {
				long offset = 0l;
				for(int i = catalogIndex; i < catalog.length; i++) {
					String[] timeOffset = catalog[i].split(",");
					long time = Long.parseLong(timeOffset[0]);
					if(time == startTime) { 
						offset = Long.parseLong(timeOffset[1]);
						break;
					}
				}
				
				logFile.seek(offset);
				
				String line;
				while((line = logFile.readLine()) != null) {
					String[] lineSplit = line.split("\t");
					if(lineSplit.length != 4) {
						continue;
					}
					
					if(lineSplit[1].equals(serverId) && lineSplit[2].equals(cpuId)) {
						sb.append("(" + startTime + " " + lineSplit[3]  + ")");
						break;
					}
					
				}
				startTime += 60;
			}
		}
		
		return sb.toString();
	}
	
	private void writeIndexToFile(Map<String, List<long[]>> serverTimeOffset) throws IOException {
		List<String> serverList = new ArrayList<>(serverTimeOffset.keySet());
		
		String indexPath = prop.getProperty("index.output.path");
		String indexFile = prop.getProperty("index.output.filename");
		
		String oldFilePath = indexPath + File.separator + indexFile;
		String newFilePath = indexPath + File.separator + indexFile + "0";
		
		try (RandomAccessFile oldFile = new RandomAccessFile(oldFilePath, "rw");
			RandomAccessFile newFile = new RandomAccessFile(newFilePath, "rw")) {
			
			for(String serverId : serverList) {
				String value = getFormattedTimeOffset(serverTimeOffset.get(serverId));
				
				if(!serverOffsetCatalog.containsKey(serverId)) {
					value = serverId + " " + value;
				} else {
					oldFile.seek(serverOffsetCatalog.get(serverId));
					String oldValue = oldFile.readLine();
					value = oldValue + " " + value;
				}
				serverOffsetCatalog.put(serverId, newFile.getFilePointer());
				newFile.writeBytes(value + System.lineSeparator());
			}
		}
		
		Files.deleteIfExists(new File(oldFilePath).toPath());
		new File(newFilePath).renameTo(new File(oldFilePath));
		
	}

	private String getFormattedTimeOffset(List<long[]> list) {
		StringBuilder sb = new StringBuilder();
		
		for(long[] tuple : list) {
			sb.append(tuple[0]);
			sb.append(",");
			sb.append(tuple[1]);
			sb.append(" ");
		}
		
		sb.setLength(sb.length() - 1);
		
		return sb.toString();
	}
	
	
}
