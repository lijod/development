package com.ld;

public class BinarySearchTree<T extends Comparable<T>> {

	class Node {
		public Node left;
		public Node right;
		public T data;
		
		public Node(T data) {
			this.data = data;
		}
		
		@Override
		public String toString() {
			return this.data.toString();
		}
		
	}
	
	public Node root;
	
	public void add(T data) {
		add(new Node(data));
	}
	
	public void add(Node node) {
		this.root = add(this.root, node);
	}
	
	public Node add(Node root, Node newNode) {
		if(root == null) {
			return newNode;
		}
		
		if(newNode.data.compareTo(root.data) < 0) {
			root.left = add(root.left, newNode);
		} else {
			root.right = add(root.right, newNode);
		}
		
		return root;
	}
	
	private void inorderTraversal(Node node, StringBuilder sb) {
		if(node == null) {
			return;
		}
		
		inorderTraversal(node.left, sb);
		sb.append(node + "->");
		inorderTraversal(node.right, sb);
	}
	
	public void delete(T data) {
		this.root =  delete(this.root, data);
	}
	
	private Node delete(Node node, T data) {
		
		if(node == null) {
			return null;
		}
		
		if(data.compareTo(node.data) < 0) {
			node.left = delete(node.left, data);
		} else if(data.compareTo(node.data) > 0) {
			node.right = delete(node.right, data);
		} else {
			if(node.left == null) {
				return node.right;
			} else if(node.right == null) {
				return node.left;
			}
			
			Node minNode = min(node.right);
			node.data = minNode.data;
			node.right = delete(node.right, node.right.data);
		}
		return node;
	}

	private Node min(Node node) {
		
		while(node != null && node.left != null) {
			node = node.left;
		}

		return node;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		inorderTraversal(root, sb);
		
		return sb.toString();
	}

	
	
}
