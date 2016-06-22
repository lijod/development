package com.ld;

public class LinkedList2D<T extends Comparable<T>> {

	Node2D head;

	/* Linked list Node */
	public class Node2D {
		T data;
		Node2D right, down;

		Node2D(T data) {
			this.data = data;
			right = null;
			down = null;
		}
	}

	public Node2D flatten(Node2D root) {

		if (root == null || root.right == null) {
			return root;
		}

		root.right = flatten(root.right);

		root = merge(root, root.right);

		return root;
	}

	private Node2D merge(Node2D node1, Node2D node2) {

		if (node1 == null && node2 == null) {
			return null;
		}

		if (node1 == null) {
			return node2;
		}

		if (node2 == null) {
			return node1;
		}

		Node2D result = null;

		if ((node1.data.compareTo(node2.data) <= 0)) {
			result = node1;
			result.down = merge(node1.down, node2);
		} else {
			result = node2;
			result.down = merge(node1, node2.down);
		}

		return result;
	}

	/*
	 * Utility function to insert a node at begining of the linked list
	 */
	Node2D push(Node2D head_ref, T data) {
		/*
		 * 1 & 2: Allocate the Node & Put in the data
		 */
		Node2D new_node = new Node2D(data);

		/* 3. Make next of new Node as head */
		new_node.down = head_ref;

		/* 4. Move the head to point to new Node */
		head_ref = new_node;

		/* 5. return to link it back */
		return head_ref;
	}

	public void printList() {
		Node2D temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.down;
		}
		System.out.println();
	}

}