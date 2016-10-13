package com.quantil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	
	private final static Properties prop = loadProperty();
	
	private PropertyUtil() {}
	
	public static Properties getProperty() {
		return prop;
	}
	
	private static Properties loadProperty() {
		InputStream inputStream = PropertyUtil.class.getResourceAsStream("app.properties");
		Properties prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return prop;
	}
	
}