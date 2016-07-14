package com.wa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wa.dao.WeatherPrefDao;
import com.wa.model.WeatherPreference;

@RestController
public class WeatherAppController {
	
	
	@Autowired
	WeatherPrefDao weatherPrefDao;
	
	@RequestMapping(name = "/addfavlocation.htm", method = RequestMethod.GET)
	public WeatherPreference getIndexPage(@RequestParam(value="zipcode") String zipcode, @RequestParam(value="name") String name, @RequestParam(value="islocal") boolean isLocal) {
		System.out.println(zipcode + " " + name + " " + isLocal);
		
		WeatherPreference pref = weatherPrefDao.addPreference(1, zipcode, isLocal, name);
		
		System.out.println(pref);
		
		return pref;
	}
}