package com.ld;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Collector {

	public static void main(String[] args) {
		
		Person p1 = new Person("Sam", 5);
		Person p2 = new Person("Tam", 1);
		Person p3 = new Person("Jam", 6);
		Person p4 = new Person("Ham", 12);
		Person p5 = new Person("Zam", 10);
		Person p6 = new Person("Lam", 7);
		Person p7 = new Person("Pam", 18);
		
		List<Person> pList1 = new ArrayList<>();
		
		pList1.add(p1);
		pList1.add(p2);
		pList1.add(p3);
		pList1.add(p4);
		pList1.add(p5);
		pList1.add(p6);
		pList1.add(p7);
		
		Map<String, Integer> newMap = pList1.stream().collect(Collectors.toMap(p -> p.getName(), p -> p.getAge()));
		
		System.out.println(newMap);
		
	}

}