package com.ld;

public class Main {

	public static void main(String[] args) {
		LinkedList<String> linkedList = new LinkedList<>(String.class);
		
		linkedList.add("A");
		linkedList.add("B");
		linkedList.add("C");
		linkedList.add("D");
		linkedList.add("E");
		linkedList.add("F");
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
		
		LinkedList<String> linkedList2 = new LinkedList<>();
		
		linkedList2.add("A");
		linkedList2.add("B");
		linkedList2.add("G");
		linkedList2.add("H");
		linkedList2.add("I");
		linkedList2.add("Z");
//		
//		System.out.println(linkedList2);
//		
//		System.out.println(linkedList.mergeSort(linkedList, linkedList2));
		
//		linkedList.deleteNodeWithData("F");
		
//		System.out.println(linkedList.isPalindrome_itr());
		
		
//		LinkedList<String>.Node node1 = new LinkedList<String>().new Node("A");
//		LinkedList<String>.Node node2 = new LinkedList<String>().new Node("B");
//		LinkedList<String>.Node node3 = new LinkedList<String>().new Node("C");
//		LinkedList<String>.Node node4 = new LinkedList<String>().new Node("D");
//		LinkedList<String>.Node node5 = new LinkedList<String>().new Node("E");
//		LinkedList<String>.Node node6 = new LinkedList<String>().new Node("F");
//		LinkedList<String>.Node node7 = new LinkedList<String>().new Node("G");
//		
//		node1.next = node2;
//		node2.next = node3;
//		node3.next = node4;
//		node5.next = node6;
//		node6.next = node7;
//		node7.next = node2;
//		
//		System.out.println(linkedList.getIntersectionNode(new LinkedList<>(node1), new LinkedList<>(node6)));
		
//		linkedList.printReverse();
		
//		linkedList.removeDuplicatesFromSorted();
		
//		linkedList.swapPairwise_itr();
//		linkedList.swapPairwise_rec();
		
//		linkedList.moveToLast();
		
		System.out.println(linkedList2);
		
		System.out.println(linkedList.getIntersection(linkedList2));
	}

}
