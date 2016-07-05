package com.ld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorting {
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

		List<Person> pList2 = new ArrayList<>();

		pList2.add(p1);
		pList2.add(p2);
		pList2.add(p3);
		pList2.add(p4);
		pList2.add(p5);
		pList2.add(p6);
		pList2.add(p7);
		
		System.out.println(pList1);
		Collections.sort(pList1, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return Integer.compare(p1.getAge(), p2.getAge());
			}
		});

		System.out.println(pList1);

		System.out.println(pList2);
		Collections.sort(pList2, (m, n) -> Integer.compare(m.getAge(), n.getAge()));
		System.out.println(pList2);

	}
}
