package com.wa.dao;

import java.util.List;

import com.wa.model.User;

public interface UserDao {

	public User addUser(User user);
	
	public User getUserById(long id);
	
	public List<User> getAllUsers();

	public User createUser(User user);

	public User getByUserName(String name);
	
}
