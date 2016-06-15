package com.ld;

public class LinkedList <T extends Comparable<T>> {

	public class Node {
		protected T data;
		protected Node next;
		
		public Node(T data) {
			this.data = data;
		}
		
		@Override
		public String toString() {
			return data.toString();
		}
	}
	
	private Node head;
	private Class<T> TClazz;
	
	public LinkedList() {}
	
	public LinkedList(Class<T> clazz) {
		this.TClazz = clazz;
	}
	
	public LinkedList(Node head) {
		this.head = head;
	}
	
	public Node add(T data) {
		Node temp = this.head;
		if(temp == null) {
			this.head = new Node(data);
			return this.head;
		}
		while(temp.next != null) {
			temp = temp.next;
		}
		
		temp.next = new Node(data);
		return temp.next;
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
		return size_itr_helper(this);
	}
	
	public int size_itr_helper(LinkedList<T> linkedList) {
		Node temp = linkedList.head;
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
	
	private Node reverseRecWithHead(Node current, Node head) {
		Node first = current;
		Node rest = current.next;
		
		if(rest == null) {
			head = first;
			return head;
		}
		
		head = reverseRecWithHead(rest, head);
		
		first.next.next = first;
		
		first.next = null;
		return head;
	}
	
	public boolean hasLoop() {
		Node slowNode = this.head;
		Node fastNode = this.head;
		
		while(fastNode != null && fastNode.next != null) {
			slowNode = slowNode.next;
			fastNode = fastNode.next.next;
			
			if(slowNode == fastNode) {
				return true;
			}
			
		}
		
		return false;
	}

	public LinkedList<T> mergeSort(LinkedList<T> ll1, LinkedList<T> ll2) {
		Node ll1Head = ll1.head;
		Node ll2Head = ll2.head;		
		
		if(ll1Head == null && ll2Head == null) {
			return  null;
		}
		
		return new LinkedList<T> (merge(ll1Head, ll2Head));
	}
	
	private Node merge(Node head1, Node head2) {
		Node result = null;
		
		if(head1 == null) {
			return head2;
		}
		
		if(head2 == null) {
			return head1;
		}
		
		if(head1.data.compareTo(head2.data) <= 0) {
			result = head1;
			result.next = merge(head1.next, head2);
		} else if(head1.data.compareTo(head2.data) > 0) {
			result = head2;
			result.next = merge(head1, head2.next);
		}
		
		return result;
	}
	
	public void deleteNodeWithData(T data) {
		Node nodeToDelete = findNodeByData(data);
		deleteCurrentNode(nodeToDelete);
	}
	
	private void deleteCurrentNode(Node nodeToDelete) {
		if(nodeToDelete == null) {
			return;
		}
		
		if(nodeToDelete.next == null) {
			nodeToDelete.next = new Node(getInstanceOfT());
		}
		
		nodeToDelete.data = nodeToDelete.next.data;
		nodeToDelete.next = nodeToDelete.next.next;
		
	}
	
	private T getInstanceOfT() {
		try {
			return TClazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Node findNodeByData(T data) {
		
		Node temp = this.head;
		while(temp != null) {
			if(temp.data.equals(data)) {
				return temp;
			}
			temp = temp.next;
		}
		
		return null;
	}
	
	public boolean isPalindrome_itr() {
		
		Node slowPointer = this.head;
		Node fastPointer = this.head;
		Node secondHalf = null;
		Node prevOfSlow = null;
		Node midNode = null;
		// Finding midpoint
		while(fastPointer != null && fastPointer.next != null) {
			fastPointer = fastPointer.next.next;
			prevOfSlow = slowPointer;
			slowPointer = slowPointer.next;
		}
		
		// Moving the slow pointer by one node to ignore the middle node 
		// in case of odd nodes
		if(fastPointer != null) {
			midNode = slowPointer;
			slowPointer = slowPointer.next;
		}
		
		secondHalf = slowPointer;
		Node firstHalf = this.head;
		// reverse the second half
		secondHalf = reverseRecWithHead(secondHalf, null);
		slowPointer = secondHalf;
		boolean isPalindrome = true;
		while(secondHalf != null) {
			if(!firstHalf.data.equals(secondHalf.data)) {
				isPalindrome = false;
				break;
			}
			firstHalf = firstHalf.next;
			secondHalf = secondHalf.next;
		}
		
		// Reverse the second half again to bring it back to original state
		slowPointer = reverseRecWithHead(slowPointer, null);
		
		// Construct the original linked list
		if(midNode != null) {
			prevOfSlow.next = midNode;
			midNode.next = slowPointer;
		} else {
			prevOfSlow.next = slowPointer;
		}
		
		return isPalindrome;
	}
	
	public void insertInSortedList(T data) {
		Node temp = this.head;
		Node newNode = new Node(data);
		
		if(temp == null || data.compareTo(temp.data) <= 0) {
			newNode.next = temp;
			this.head = newNode;
			return;
		}
		
		Node prev = null;
		while(temp != null && temp.next != null) {
			prev = temp;
			temp = temp.next;
			if(data.compareTo(temp.data) <= 0) {
				newNode.next = temp;
				return;
			}
		}
		
		temp.next = newNode;		
	}
	
	public T getIntersectionNode(LinkedList<T> ll1, LinkedList<T> ll2) {
		
		int c1 = size_itr_helper(ll1);
		int c2 = size_itr_helper(ll2);
		
		if(c1 == 0 || c2 == 0) {
			return null;
		}
		
		if(c1 >= c2) {
			return getIntersectionNodeHelper(c1 - c2, ll1.head, ll2.head);
		} else {
			return getIntersectionNodeHelper(c2 - c1, ll2.head, ll1.head);
		}
		
	}
	
	
	private T getIntersectionNodeHelper(int lenDiff, Node node1, Node node2) {
		while(lenDiff-- > 0) {
			if(node1 == null) {
				return null;
			}
			node1 = node1.next;
		}
		
		while(node1 != null && node2 != null) {
			if(node1 == node2) {				
				return node1.data;
			}
			node1 = node1.next;
			node2 = node2.next;
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
			Node temp = this.head;
			while(temp != null) {
				sb.append(temp.data.toString()).append("->");
				temp = temp.next;
			}
			return sb.toString();
		}		
	}
}
