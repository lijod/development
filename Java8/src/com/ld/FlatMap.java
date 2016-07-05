package com.ld;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FlatMap {

	public static void main(String[] args) {
		Person p1 = new Person("Sam", 5);
		p1.addPhoneNumber(new PhoneNumber("111111111111111"));
		p1.addPhoneNumber(new PhoneNumber("111122222222222"));
		Person p2 = new Person("Tam", 1);
		Person p3 = new Person("Jam", 6);
		p3.addPhoneNumber(new PhoneNumber("333333333333333"));
		Person p4 = new Person("Ham", 12);
		Person p5 = new Person("Zam", 10);
		Person p6 = new Person("Lam", 7);
		p6.addPhoneNumber(new PhoneNumber("666666666666666"));
		p6.addPhoneNumber(new PhoneNumber("666666111111111"));
		p6.addPhoneNumber(new PhoneNumber("666666122222222"));
		Person p7 = new Person("Pam", 18);
		
		List<Person> pList1 = new ArrayList<>();
		
		pList1.add(p1);
		pList1.add(p2);
		pList1.add(p3);
		pList1.add(p4);
		pList1.add(p5);
		pList1.add(p6);
		pList1.add(p7);
		
		long mil = System.currentTimeMillis();
		Stream<PhoneNumber> stream = pList1.stream().parallel().flatMap(p -> p.getPhoneNumbers().stream());
		
		stream.forEach(System.out:: println);
		
		System.out.println(System.currentTimeMillis() - mil);
	}

}
