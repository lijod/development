package com.ld;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.xml.soap.Node;

public class BinaryTree<T> {

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

	public void inorderTraversal(Node node) {
		if (node == null) {
			return;
		}

		inorderTraversal(node.left);

		System.out.println(node + "->");

		inorderTraversal(node.right);

	}

	public void preorderTraversal(Node node) {
		if (node == null) {
			return;
		}

		System.out.println(node + "->");

		preorderTraversal(node.left);

		preorderTraversal(node.right);

	}

	public void postorderTraversal(Node node) {
		if (node == null) {
			return;
		}

		postorderTraversal(node.left);

		postorderTraversal(node.right);

		System.out.println(node + "->");

	}

	public void preorder_itr(Node root) {

		if (root == null) {
			return;
		}

		Stack<Node> stack = new Stack<>();

		stack.push(root);

		while (!stack.isEmpty()) {

			Node node = stack.pop();

			System.out.println(node);

			if (node.right != null) {
				stack.push(node.right);
			}

			if (node.left != null) {
				stack.push(node.left);
			}

		}
	}

	public void inorder_itr(Node root) {

		if (root == null) {
			return;
		}

		Stack<Node> stack = new Stack<>();

		Node node = root;
		
		while(node != null) {
			stack.push(node);
			node = node.left;
		}

		while (!stack.isEmpty()) {
			  node = stack.pop();
			  System.out.println(node);
			  if(node.right != null) {
				  node = node.right;
				  while(node != null) {
					  stack.push(node);
					  node = node.left;
				  }
			  }
		}

	}
	
	public int getHeight(Node root) {
		
		if(root == null) {
			return 0;
		}
		
		int leftHeight = getHeight(root.left);
		int rightHeight = getHeight(root.right);
		
		
		return 1 + Math.max(leftHeight, rightHeight);	
	}
	
	public int getWidth(Node root) {
		
		int maxWidth = 1;
		Queue<Node> nodeQueue = new LinkedList<>();
		nodeQueue.add(root);
		nodeQueue.add(null);
		
		while(!nodeQueue.isEmpty()) {
			Node currNode = nodeQueue.remove();
			
			if(currNode == null) {
				int nextRowCount = nodeQueue.size();
				if(nextRowCount > 0) {
					if(nextRowCount > maxWidth) {
						maxWidth = nextRowCount;
					}
					nodeQueue.add(null);
				}
			} else {
				if(currNode.left != null) {
					nodeQueue.add(currNode.left);
				}
				if(currNode.right != null) {
					nodeQueue.add(currNode.right);
				}
			}
			
		}
		
		return maxWidth;
	}

	public int getDiameter(Node node) {
		if(node == null) {
			return 0;
		}
		
		int lHeight = getHeight(node.left);
		int rHeight = getHeight(node.right);
		
		int lDiameter = getDiameter(node.left);
		int rDiameter = getDiameter(node.right);
		
		return Math.max(lHeight + rHeight, Math.max(lDiameter, rDiameter));
		
	}
	
}