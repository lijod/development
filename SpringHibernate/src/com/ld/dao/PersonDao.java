package com.ld.dao;

import java.util.List;

import com.ld.model.Person;

public interface PersonDao {

	public void save(Person p);
	
	public List<Person> list();
	
}