package com.ld;

import java.util.ArrayList;
import java.util.List;

public class Person{
	private String name;
	private int age;
	List<PhoneNumber> phoneNumbers;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
		phoneNumbers = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void addPhoneNumber(PhoneNumber ph) {
		this.phoneNumbers.add(ph);
	}
	
	@Override
	public String toString() {
		return " [name=" + name + ", age=" + age + "] ";
	}	
}