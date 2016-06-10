package com.ld;

public class Main {

	public static void main(String[] args) {
		LinkedList<String> linkedList = new LinkedList<>();
		
		linkedList.add("A");
		linkedList.add("B");
		linkedList.add("C");
		
		System.out.println(linkedList);
		System.out.println(linkedList.size_itr());
		System.out.println(linkedList.size_rec());
		
		linkedList.delete("B");
		
		System.out.println(linkedList);
		System.out.println(linkedList.size_itr());
		System.out.println(linkedList.size_rec());
	}

}
