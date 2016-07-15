package com.wa.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
		System.out.println("user by id: " + id);
		return userDao.getUserById(id);
	}
	
}
