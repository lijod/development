package com.quantil;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	
	public static void writeToFile(String path, String content, boolean append) {
		
		try(FileWriter writer = new FileWriter(path, append)) {
			writer.write(content);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
