package com.ld;

public class LinkedList <T> {

	private class Node {
		protected T data;
		protected Node next;
		
		public Node(T data) {
			this.data = data;
		}
	}
	
	private Node head;
	
	public void add(T data) {
		Node temp = this.head;
		if(temp == null) {
			this.head = new Node(data);
			return;
		}
		while(temp.next != null) {
			temp = temp.next;
		}
		
		temp.next = new Node(data);
	}
	
	public void delete(T data) {
		Node temp = this.head;
		
		if(temp == null) {
			return;
		}
		
		if(temp.data.equals(data)) {
			this.head = this.head.next;
			return;
		}
		
		Node prev = null;
		while(temp != null) {
			 if(temp.data.equals(data)) {
				 prev.next = temp.next;
				 break;
			 }
			 prev = temp;
			 temp = temp.next;
		}
	}
	
	public int size_itr() {
		Node temp = this.head;
		if(temp == null) {
			return 0;
		}
		int count = 0;
		while(temp != null) {
			count++;
			temp = temp.next;
		}
		
		return count;
	}
	
	public int size_rec() {
		Node temp = this.head;
		if(temp == null) {
			return 0;
		}
		
		return size_rec_helper(temp);
	}
	
	private int size_rec_helper(Node node) {
		if(node == null) return 0;
		return 1 + size_rec_helper(node.next);
	}
	
	public boolean hasElement_itr(T value) {
		Node temp = this.head;
		
		if(temp == null) {
			return false;
		}
		
		while(temp != null) {
			if(temp.data.equals(value)) {
				return true;
			}
			temp = temp.next;
		}
		
		return false;
	}
	
	public boolean hasElement_rec(T value) {
		Node temp = this.head;
		
		if(temp == null) {
			return false;
		}
		
		return hasElementHelper(temp, value);
	}
	
	private boolean hasElementHelper(Node node, T value) {
		if(node == null) {
			return false;
		}
		
		if(node.data.equals(value)) {
			return true;
		}
		
		return false || hasElementHelper(node.next, value);
	}
	
	public void swap(T value1, T value2) {
		
		Node curr1 = null;
		Node curr2 = null;
		Node prev1 = null;
		Node prev2 = null;
		
		Node temp = this.head;
		
		if(temp == null) {
			return;
		}
		
		// Find the first element and its previous element
		while(temp != null) {
			if(temp.data.equals(value1)) {
				curr1 = temp;
				break;
			}
			prev1 = temp;
			temp = temp.next;
		}
		
		temp = this.head;
		
		// Find the second element and its previous element
		while(temp != null) {
			if(temp.data.equals(value2)) {
				curr2 = temp;
				break;
			}
			prev2 = temp;
			temp = temp.next;
		}
		
		// Check if both are present in the list
		if(curr1 == null || curr2 == null) {
			return;
		}
		
		// If curr1 is the head, make curr2 the head
		if(prev1 == null) {
			this.head = curr2;
		} else {
			// If curr1 is not the head, point prev1.next to curr2
			prev1.next = curr2;
		}
		
		// If curr2 is the head, make curr1 the head
		if(prev2 == null) {
			this.head = curr1;
		} else {
			// If curr2 is not the head, point prev2.next to curr1
			prev2.next = curr1;
		}
		
		// Swap next element of both curr1 and curr2
		Node tempNext = curr1.next;
		curr1.next = curr2.next;
		curr2.next = tempNext;
		
	}
	
	public T findMiddleElement() {
		
		if(this.head == null) {
			return null;
		}
		
		Node slowPointer = this.head;
		Node fastPointer = this.head;
		
		while(fastPointer != null && fastPointer.next != null) {
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;
		}
		
		return slowPointer != null ? slowPointer.data : null;
	}
	
	public T findNthElementFromEnd(int n) {
		Node slowPointer = this.head;
		Node fastPointer = this.head;
		int counter = 0;
		
		if(this.head== null || n <= 0) {
			return null;
		}
		
		while(counter < n) {
			if(fastPointer == null) {
				return null;
			}
			fastPointer = fastPointer.next;
			counter++;
		}
		
		while(fastPointer != null) {
			fastPointer = fastPointer.next;
			slowPointer = slowPointer.next;
		}
		
		return slowPointer.data;
	} 

	public void reverse_itr() {
		
		if(this.head == null) {
			return;
		}
		
		Node prev = null;
		Node current = this.head;
		Node next = null;
		
		while(current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		
		this.head = prev;
		
	}
	
	public void reverse_rec() {
		if(this.head == null) {
			return;
		}
		
		reverseRecHelper(this.head);
		
	}
	
	private void reverseRecHelper(Node current) {
		Node first = current;
		Node rest = current.next;
		
		if(rest == null) {
			this.head = first;
			return;
		}
		
		reverseRecHelper(rest);
		
		first.next.next = first;
		
		first.next = null;
		
	}

	@Override
	public String toString() {
		if(this.head == null) {
			return "null";
		} else if(this.head.next == null) {
			return this.head.data.toString();
		} else {
			StringBuilder sb = new StringBuilder();
			Node temp = this.head;
			while(temp != null) {
				sb.append(temp.data.toString()).append("->");
				temp = temp.next;
			}
			return sb.toString();
		}		
	}
}
