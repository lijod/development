package com.ld;

public class KStackUsingArray {
	int[] arr;
	int[] top;
	int[] next;
	int free;
	
	public KStackUsingArray(int k, int n) {
		if(k > n) {
			throw new StackOverflowError("Array size should be more than stack count!");
		}
		free = 0;
		arr = new int[n];
		top = new int[k];
		next = new int[n];
		
		for(int i = 0; i < k; i++) {
			top[i] = -1;
		}
		
		for(int i = 0; i < n-1; i++) {
			next[i] = i + 1;
		}
		
		next[n - 1] = -1;
		
	}
	
	/**
	 * stackNo -> 0 to k-1
	 */
	public void push(int value, int stackNo) {
		
		if(free == -1) {
			System.out.println("Stack is full");
			return;
		}
		
		int prevFree = free;
		
		free = next[prevFree];
		
		next[prevFree] = top[stackNo];
		
		top[stackNo] = prevFree;
		
		arr[prevFree] = value;
		
	}
	
	public int pop(int stackNo) {
		int prevTop = top[stackNo];
		
		if(prevTop == -1) {
			System.out.println("This stack is empty, returning garbage!");
			return -1;
		}
		
		top[stackNo] = next[prevTop];
		
		next[prevTop] = free;
		
		free = prevTop;
		
		return arr[prevTop];
		
	}
	
	@Override
	public String toString() {
		return "Array-> " + getText(arr) + " \n" + 
				"Top-> " + getText(top) + " \n" +
				"Next-> " + getText(next) + "\n" + 
				"Free-> " + free +
				"\n";
	}

	private String getText(int[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int i : arr) {
			sb.append(i + ":");
		}
		
		return sb.toString();
	}
	
	
}