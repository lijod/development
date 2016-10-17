package com.quantil;

import java.io.FileWriter;
import java.io.IOException;

/**
 * FileUtil class contains static utility methods for I/O operation
 * @author lijodaniel
 *
 */
public class FileUtil {
	
	/**
	 * Writes/appends content to the specified path
	 * @param path Output file path
	 * @param content Content to be written
	 * @param append If true, appends the content, else overwrites
	 */
	public static void writeToFile(String path, String content, boolean append) {
		
		try(FileWriter writer = new FileWriter(path, append)) {
			writer.write(content);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
