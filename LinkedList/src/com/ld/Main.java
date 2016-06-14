package com.ld;

public class Main {

	public static void main(String[] args) {
		LinkedList<String> linkedList = new LinkedList<>(String.class);
		
		linkedList.add("A");
		linkedList.add("B");
		linkedList.add("G");
		linkedList.add("H");
		linkedList.add("I");
		linkedList.add("Z");
//		
		
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
		
//		linkedList.reverse_rec();
		
//		LinkedList<String> linkedList2 = new LinkedList<>();
//		
//		linkedList2.add("A");
//		linkedList2.add("B");
//		linkedList2.add("G");
//		linkedList2.add("H");
//		linkedList2.add("I");
//		linkedList2.add("Z");
//		
//		System.out.println(linkedList2);
//		
//		System.out.println(linkedList.mergeSort(linkedList, linkedList2));
		
//		linkedList.deleteNodeWithData("F");
		
		System.out.println(linkedList.isPalindrome_itr());
	}

}
