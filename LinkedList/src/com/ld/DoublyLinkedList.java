package com.ld;

import com.ld.LinkedList.Node;

public class DoublyLinkedList<T> {
	
	public class DNode{
		public DNode prev;
		public DNode next;
		
		public T data;
	}
	
	public DNode head = null;
	
	public DNode reverse(DNode head) {
		
		if(head == null) {
			return null;
		}
		
		DNode curr = head;
		DNode prev = null;
		
		while(curr != null) {
			prev = curr.prev;
			curr.prev = curr.next;
			curr.next = prev;
			curr = curr.prev;
		}
		
		if(prev != null) {
			this.head = prev.prev;
			return prev.prev;
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		if(this.head == null) {
			return null;
		} else if(this.head.next == null) {
			return this.head.data.toString();
		} else {
			StringBuilder sb = new StringBuilder();
			DNode temp = this.head;
			while(temp != null) {
				sb.append(temp.data.toString()).append("->");
				temp = temp.next;
			}
			return sb.toString();
		}		
	}
	
	
}