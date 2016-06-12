package com.ld;

public class Main {

	public static void main(String[] args) {
		LinkedList<String> linkedList = new LinkedList<>();
		
		linkedList.add("A");
		linkedList.add("B");
		linkedList.add("C");
		linkedList.add("D");
		linkedList.add("E");
		linkedList.add("F");
		
		System.out.println(linkedList);
		
//		System.out.println(linkedList.hasElement_itr("B"));
//		System.out.println(linkedList.hasElement_rec("C"));
//		
//		System.out.println(linkedList.size_itr());
//		System.out.println(linkedList.size_rec());
//		
//		linkedList.delete("B");
//		
//		System.out.println(linkedList);
//		System.out.println(linkedList.size_itr());
//		System.out.println(linkedList.size_rec());
//		
//		linkedList.swap("F", "E");
//		
//		System.out.println(linkedList);
		
//		System.out.println(linkedList.findMiddleElement());
//		System.out.println(linkedList.findNthElementFromEnd(0));
		
		linkedList.reverse_rec();
		
		System.out.println(linkedList);
	}

}
