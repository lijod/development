package com.ld.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ld.model.Person;

@Service("personDao") 
public interface PersonDao {

	public void save(Person p);
	
	public List<Person> list();
	
}