package com.wa.dao;

import com.wa.model.WeatherPreference;

public interface WeatherPrefDao {	
    
	public WeatherPreference addPreference(long userId, String zipcode, boolean isLocal, String name);
	
	public WeatherPreference removePreference(WeatherPreference pref);
	
	public void editPreference(WeatherPreference pref, String name);
	
}
