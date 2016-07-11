package com.ld;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ld.dao.PersonDao;
import com.ld.model.Person;

public class Main {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-hibernate4.xml");
		
		PersonDao personDAO = context.getBean(PersonDao.class);
		
//		Person person = new Person();
//		person.setId(3);
//		person.setName("Parth"); 
////		person.setCountry("India");
//		
//		personDAO.save(person);
//		
//		System.out.println("Person::"+person);
		
		List<Person> list = personDAO.list();
		
		for(Person p : list){
			System.out.println("Person List::"+p);
		}
		//close resources
		context.close();	
	}
}
