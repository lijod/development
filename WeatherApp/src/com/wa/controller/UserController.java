package com.wa.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wa.dao.UserDao;
import com.wa.model.User;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/getuserbyid.htm", method=RequestMethod.GET)
	public User getUserById(@RequestParam(value="id") long id) {
		return userDao.getUserById(id);
	}
	
	@RequestMapping(value="/register.htm", method=RequestMethod.POST)
	public User registerUser(@RequestBody User user) {
		return userDao.createUser(user);
	}

	@RequestMapping(value="/currentuser.htm", method=RequestMethod.GET)
	public User getCurrentSessionUser(Principal principal) {
		if(principal != null) {
			String userName = principal.getName();
			User user = userDao.getByUserName(userName);
			user.setPassword(null);
			return user;
		}
		
		return null;
	} 
	
}
