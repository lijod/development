package com.ld;

public class Main {

	public static void main(String[] args) {
		
		BinaryTree<Integer> binaryTree = new BinaryTree<>();
		
		BinaryTree<Integer>.Node root = new BinaryTree<Integer>().new Node(0);
		BinaryTree<Integer>.Node node1 = new BinaryTree<Integer>().new Node(1);
		BinaryTree<Integer>.Node node2 = new BinaryTree<Integer>().new Node(2);
		BinaryTree<Integer>.Node node3 = new BinaryTree<Integer>().new Node(3);
		BinaryTree<Integer>.Node node4 = new BinaryTree<Integer>().new Node(4);
		BinaryTree<Integer>.Node node5 = new BinaryTree<Integer>().new Node(5);
		BinaryTree<Integer>.Node node6 = new BinaryTree<Integer>().new Node(6);
		BinaryTree<Integer>.Node node7 = new BinaryTree<Integer>().new Node(7);
		BinaryTree<Integer>.Node node8 = new BinaryTree<Integer>().new Node(8);
		BinaryTree<Integer>.Node node9 = new BinaryTree<Integer>().new Node(9);
		
		
		root.left = node1;
		root.right = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
//		node4.left = node6;
//		node6.left = node7;
//		node5.right = node8;
//		node8.right = node9;
		System.out.println("PreOrder: ");
		binaryTree.preorderTraversal(root);
		
		System.out.println("PreOrder Iterative: ");
		binaryTree.preorder_itr(root);
		

		System.out.println("InOrder: ");
		binaryTree.inorderTraversal(root);
		
		System.out.println("InOrder Iterative: ");
		binaryTree.inorder_itr(root);
		
		System.out.println("PostOrder: ");
		binaryTree.postorderTraversal(root);
		
		System.out.println("Height:" + binaryTree.getHeight(root));
		
		System.out.println("Width:" + binaryTree.getWidth(root));
		
		System.out.println("Diameter:" + binaryTree.getDiameter(root));
		
	}

}