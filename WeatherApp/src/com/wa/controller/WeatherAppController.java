package com.wa.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wa.dao.UserDao;
import com.wa.dao.WeatherPrefDao;
import com.wa.model.User;
import com.wa.model.WeatherPreference;

@RestController
@RequestMapping(value="/weather")
public class WeatherAppController {

	@Autowired
	WeatherPrefDao weatherPrefDao;
	
	@Autowired
	UserDao userDao;

	@RequestMapping(value = "/addfavlocation.htm", method = RequestMethod.GET)
	public WeatherPreference addFavoriteLocation(@RequestParam(value = "zipcode") String zipcode,
			@RequestParam(value = "name") String name, @RequestParam(value = "islocal") boolean isLocal, Principal principal) {
		System.out.println(zipcode + " " + name + " " + isLocal);
		
		if(principal != null) {
			String username = principal.getName();
			User user = userDao.getByUserName(username);
			WeatherPreference pref = weatherPrefDao.addPreference(user.getUserId(), zipcode, isLocal, name);
			System.out.println(pref);
			return pref;
		}
		
		return null;
	}
	
	@RequestMapping(value = "/removefavlocation.htm", method = RequestMethod.POST)
	public WeatherPreference deleteFavoriteLocation(@RequestBody WeatherPreference pref) {
		return weatherPrefDao.removePreference(pref);
	}
	
}