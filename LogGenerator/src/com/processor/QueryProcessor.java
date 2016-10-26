package com.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * QueryProcessor class lets you query CPU usage for a specific CPU for a specific 
 * in a given time period. It is an interactive command line tool which reads a  
 * user’s commands from stdin.
 * @author lijodaniel
 */
public class QueryProcessor {
	
	/**
	 * This hash map stores the file offset for a server entry in the catalog file.
	 * Example:
	 * <key, value> => <192.168.0.1, 1701144>
	 */
	private final Map<String, Long> serverOffsetCatalog;
	
	public QueryProcessor(String appDir) {
		serverOffsetCatalog = new HashMap<>();
		Utility.setAppDir(appDir);
	}
	
	/**
	 * Create catalog for log data and initialize the program to processes user query
	 */
	public void loadProcessor() {
		boolean catalogCreated = generateServerCatalog();
		if(catalogCreated) {
			process();
		} else {
			System.out.println("An error occured when loading log file, please try again!");
		}
	}
	
	/**
	 * Generate a catalog with serverId as 1st field and tuples of timestamp and 
	 * offset in log file
	 * Format: <Server IP> <timestamp1,offset1> <timestamp2,offset2> ...
	 * @return
	 */
	private boolean generateServerCatalog() {
		System.out.println("Loading log file for query processing, estimated time: 3-4 mins...");
		
		long start = System.currentTimeMillis();
		int uniqueTimeCount = 0;
		
		// Holds server ip as key and list of tuples (timestamp, offset) as value.
		// This will be flushed to catalog file
		// Key = <Server IP>  Value= [<timestamp1,offset1> <timestamp2,offset2>...]
		HashMap<String, List<long[]>> serverTimeOffset = new LinkedHashMap<>();
		
		String logPath = Utility.getLogPath();
		
		try (RandomAccessFile file = new RandomAccessFile(logPath, AppConstants.ACCESSTYPE_READ)) {
			// Skipping the header line
			file.readLine();
			
			String logLine;
			long prevTime = 0L;
			long logOffset = file.getFilePointer();
			String prevServerId = "";
			while((logLine = file.readLine()) != null) {
				
				String[] logLineSplit = logLine.split("\t");
				if(logLineSplit.length != 4) {
					logOffset = file.getFilePointer();
					continue;
				}
				
				String serverId = logLineSplit[1];
				
				// Only saves offset for 1st ip occurrence
				if(prevServerId.equals(serverId)) {
					logOffset = file.getFilePointer();
					continue;
				}
				
				long currTime = Long.parseLong(logLineSplit[0]);
				
				// Encountered a new time
				if(prevTime != currTime) {
					uniqueTimeCount++;
					prevTime = currTime;
					// Flush record for every 100 minute
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
				
				list.add(new long[]{currTime, logOffset});
				
				prevServerId = serverId;
				logOffset = file.getFilePointer();
				
			}
			writeIndexToFile(serverTimeOffset);
		} catch(Exception e) {
			System.out.println("Error occured while processing, generate new log and try again!");
			System.out.println(e.getMessage());
			return false;
		}
		
		long end = System.currentTimeMillis();
		System.out.println(String.format("Total time (sec): %d", ((end - start) / 1000)));
		
		return true;
	}
	
	/**
	 * Loads the Query execution for the application and accepts input to be processed and
	 * prints the usage as per input
	 */
	private void process() {
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			while(true) {
				try {
					printInputOptions();
					
					String input = br.readLine();
					long start = System.currentTimeMillis();
					if(input.equalsIgnoreCase("EXIT")) {
						System.out.println("Exitting QueryProcessor!");
						return;
					} else if (input.startsWith("QUERY")) {
						System.out.println("Querying...");
						String[] inputSplit = input.split(" +");
						
						if(inputSplit.length != 7) {
							System.out.println("Invalid input for query, please try again...");
							continue;
						}
						
						long startTime = Utility.getUnixTime(inputSplit[3] + " " + inputSplit[4], AppConstants.DATE_TYPE_yyyyMMddHHmm);
						long endTime = Utility.getUnixTime(inputSplit[5] + " " + inputSplit[6], AppConstants.DATE_TYPE_yyyyMMddHHmm);
						
						if(startTime > endTime) {
							throw new IllegalArgumentException("End time should be after Start time, please try again!");
						}
						
						System.out.println(String.format("CPU %s usage on: %s", inputSplit[2], inputSplit[1]));
						System.out.println(getUsage(inputSplit[1], inputSplit[2], startTime, endTime));
						
						long end = System.currentTimeMillis();
						
						System.out.println(String.format("Total query time (ms): %d", (end - start)));
					} else {
						System.out.println("Input invalid!");
						continue;
					}
				
				} catch(IllegalArgumentException e) {
					System.out.println(e.getMessage());
				} catch (ParseException e) {
					System.out.println("Time format invalid, please check you input...");
				} catch (IOException e) {
					System.out.println("Unable to load log file for quering!");
				} catch (Exception e) {
					System.out.println("Unknown Error, please try again!");
					System.out.println(e.getMessage());
				}
			}
			
		} catch (IOException e) {
			System.out.println("Unable to load log file while quering!");
		} 
		
	}

	/**
	 * 
	 * @param serverId ServerId in the query
	 * @param cpuId CPU id in the query
	 * @param startTime Start time in unix time format
	 * @param endTime Start time in unix time format
	 * @return Usage of CPU with cpuID in server with serverId between startTime and endTime
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private String getUsage(String serverId, String cpuId, long startTime, long endTime) throws IOException {
		String logPath = Utility.getLogPath();
		String catalogPath = Utility.getCatalogPath();
		SimpleDateFormat dateFormat = new SimpleDateFormat(AppConstants.DATE_TYPE_yyyyMMddHHmm);
		StringBuilder sb = new StringBuilder();
		
		// Open log and catalog file for reading
		try (RandomAccessFile logFile = new RandomAccessFile(logPath, AppConstants.ACCESSTYPE_READ);
			RandomAccessFile catalogFile = new RandomAccessFile(catalogPath, AppConstants.ACCESSTYPE_READ)) {
			
			// Point to server in catalog file
			if(serverOffsetCatalog.containsKey(serverId)) {
				catalogFile.seek(serverOffsetCatalog.get(serverId));
			} else {
				throw new IllegalArgumentException("Server IP is not valid, please try again!");
			}
			
			String serverInfo = catalogFile.readLine();
			String[] catalog = serverInfo.split(" ");
			int catalogIndex = getStartTimeIndex(catalog, startTime);
			
			if(catalogIndex == -1) {
				throw new IllegalArgumentException("Start time entered is not valid, please try again!");
			}
			
			// Get log usage for startTime and increment it by a min (60 sec) until 
			// endTime is reached
			while(startTime <= endTime) {
				long offset = 0l;
				
				// Get offset for startTime in catalog for server, starting looking from 
				// catalogIndex position
				for(int i = catalogIndex; i < catalog.length; i++) {
					String[] timeOffset = catalog[i].split(",");
					long time = Long.parseLong(timeOffset[0]);
					if(time == startTime) { 
						offset = Long.parseLong(timeOffset[1]);
						break;
					}
				}
				
				// Point to serverId with startTime in log file
				logFile.seek(offset);
				
				// Get usage for CPU
				String line;
				while((line = logFile.readLine()) != null) {
					String[] lineSplit = line.split("\t");
					if(lineSplit.length != 4) {
						continue;
					}
					
					if(!lineSplit[1].equals(serverId)) {
						throw new IllegalArgumentException("Invalid CPU ID, please try again!");
					}
					
					if(lineSplit[2].equals(cpuId)) {
						Date date = new Date((long) startTime * 1000);
						sb.append(getFormattedQueryResult(dateFormat.format(date), lineSplit[3]));
						break;
					}
				}
				
				// Increment start time by a minute
				startTime += 60;
			}
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch(Exception e) {
			System.out.println("An error occured while processing the query, please try again!");
			e.printStackTrace();
		}
		
		if(sb.length() >= 2) {
			sb.setLength(sb.length() - 2);
		}
		
		return sb.toString();
	}
	
	/**
	 * Flushes the data from input map to catalog file
	 * Format: <Server IP> <timestamp1,offset1> <timestamp2,offset2> ...
	 * @param serverTimeOffset
	 * @throws IOException
	 */
	private void writeIndexToFile(Map<String, List<long[]>> serverTimeOffset) throws IOException {
		Set<String> serverList = serverTimeOffset.keySet();
		
		String oldCatalogPath = Utility.getCatalogPath();
		String newCatalogPath = Utility.getCatalogPath() + "0";
		
		try (RandomAccessFile oldFile = new RandomAccessFile(oldCatalogPath, AppConstants.ACCESSTYPE_READ_WRITE);
			RandomAccessFile newFile = new RandomAccessFile(newCatalogPath, AppConstants.ACCESSTYPE_READ_WRITE)) {
			
			for(String serverId : serverList) {
				String value = getFormattedTimeOffset(serverTimeOffset.get(serverId));
				
				if(!serverOffsetCatalog.containsKey(serverId)) {
					value = serverId + " " + value;
				} else {
					oldFile.seek(serverOffsetCatalog.get(serverId));
					String oldValue = oldFile.readLine();
					value = oldValue + " " + value;
				}
				// Updates the new offset for current Server IP address
				serverOffsetCatalog.put(serverId, newFile.getFilePointer());
				newFile.writeBytes(value + System.lineSeparator());
			}
		}
		
		Files.deleteIfExists(new File(oldCatalogPath).toPath());
		new File(newCatalogPath).renameTo(new File(oldCatalogPath));
		
	}

	/**
	 * Returns a string representation of <timestamp,offset> tuple in the input list
	 * separated by single space
	 * @param list List of tuples with 
	 * @return
	 */
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
	
	/**
	 * Does a binary search on catalog array to find the start time, 
	 * returns index if found else -1
	 * @param catalog Array with tuples <TIMESTAMP,OFFSET>
	 * @param startTime Start time to be search
	 * @return Index of start time in input array
	 */
	private int getStartTimeIndex(String[] catalog, long startTime) {
		
		int start = 1;
		int end = catalog.length - 1;
		
		while(start <= end) {
			int mid = start + (end - start) / 2;
			
			String tuple = catalog[mid];
			long currTime = Long.parseLong(tuple.split(",")[0]);
			
			if(startTime == currTime) {
				return mid;
			} else if(startTime < currTime) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
			
		}
		
		return -1;
		
	}
	
	/**
	 * Returns a string representation of <date,usage>
	 * Format: (<Date>, <Usage>%) 
	 * @param date Date-Time of log
	 * @param usage Percent used
	 * @return String representation of 
	 */
	private String getFormattedQueryResult(String date, String usage) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("(");
		sb.append(date);
		sb.append(", ");
		sb.append(usage);
		sb.append("%), ");
		
		return sb.toString();
	}
	
	/**
	 * Prints query options to STD OUT
	 */
	private void printInputOptions() {
		System.out.println();
		System.out.println("Please enter one of the options:");
		System.out.println("1. ");
		System.out.println("QUERY <SERVER IP> <CPU ID> <START TIME> <END TIME>");
		System.out.println("2. ");
		System.out.println("EXIT");
		System.out.print(">");
	}
	
}
