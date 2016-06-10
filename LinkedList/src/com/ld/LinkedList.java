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
	
	@Override
	public String toString() {
		if(this.head == null) {
			return "";
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
