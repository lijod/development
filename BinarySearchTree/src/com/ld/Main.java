package com.ld;

public class Main {

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		
		bst.add(50);
		bst.add(30);
		bst.add(60);
		bst.add(40);
		bst.add(10);
		bst.add(70);
		
		System.out.println(bst);
		
//		bst.delete(30);
		
//		System.out.println(bst);
		
		System.out.println(bst.getLCA(40, 80));
		
		System.out.println("isPresent: " + bst.isPresent(50));
		
		System.out.println("k smallest value: " + bst.getKSmallest(3));
		
	}
	
}
