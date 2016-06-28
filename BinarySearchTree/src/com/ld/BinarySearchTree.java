package com.ld;

import java.util.Stack;

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
	
	public T getLCA(T val1, T val2) {
		Node node = getLCA(this.root, val1, val2);
		return node != null  ? node.data : null;
	}

	private Node getLCA(Node node, T val1, T val2) {
		
		if(node == null) {
			return null;
		}
		
		if(!isPresent(val1) || !isPresent(val2)) {
			return null;
		}
		
		if(node.data.compareTo(val1) > 0 && node.data.compareTo(val2) > 0) {
			return getLCA(node.left, val1, val2);
		} else if(node.data.compareTo(val1) < 0 && node.data.compareTo(val2) < 0) {
			return getLCA(node.right, val1, val2);
		}
		
		return node;
	}
	
	public boolean isPresent(T data) {
		return isPresent(this.root, data);
	}
	

	private boolean isPresent(Node node, T data) {
		
		if(node == null) {
			return false;
		}
		
		if(node.data.compareTo(data) > 0) {
			return isPresent(node.left, data);
		} else if(node.data.compareTo(data) < 0) {
			return isPresent(node.right, data);
		}
		
		return true;
	}

	public T getKSmallest(int k) {
		return getKSmallest(this.root, k);
	}
	
	private T getKSmallest(Node node, int k) {
		
		Stack<Node> stack = new Stack<>();
		
		while(!stack.isEmpty() || node != null) {
			if(node != null) {
				stack.push(node);
				node = node.left;
			} else {
				Node temp = stack.pop();
				k--;
				if(k == 0) {
					return temp.data;
				}
				node = temp.right;
			}
		}
		
		return null;
	}
	
//	private T getKSmallest_logn(Node node, int k) {
//		
//		int count = k;
//		
//		while(node != null) {
//			int sizeOfLeftSubtree = size(node.left);
//			
//			if()
//			
//		}
//		
//		return null;
//	}
	
	public int size(Node node) {
		if(node == null) {
			return 0;
		}
		
		return size(node.left) + 1 + size(node.right);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		inorderTraversal(root, sb);
		
		return sb.toString();
	}

	
	
}
