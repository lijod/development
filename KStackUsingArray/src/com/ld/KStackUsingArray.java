package com.ld;

public class KStackUsingArray<T> {
	int[] arr;
	int[] top;
	int[] next;
	int free;
	
	public KStackUsingArray(int k, int n) {
		if(k > n) {
			throw new StackOverflowError("Array size should be more than stack count!");
		}
		
		arr = new int[n];
		
		
	}
}