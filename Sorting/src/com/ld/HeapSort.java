package com.ld;

import java.util.Arrays;

public class HeapSort {

	public static void main(String[] args) {
		int[] arr = {5,3,4,1,6};
		
		sort(arr);
		
		System.out.println(Arrays.toString(arr));
	}

	private static void sort(int[] arr) {
		
		// Max-heapify the whole array
		for(int i = arr.length / 2 - 1; i >= 0; i--) {
			heapify(arr, arr.length, i);
		}
		
		// Swap the max with last element and heapify, decrement by one for next iteration which means 
		// ignore the max element for next heapification
		for(int i  = arr.length - 1; i > 0; i--) {
			
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			
			heapify(arr, i, 0);
			
		}
		
	}
	
	private static void heapify(int[] arr, int size, int start) {
		
		int largest = start;
		int left = 2 * start + 1; 
		int right = 2 * start + 2; 
		
		//Find largest between parent and children
		if(left < size && arr[left] > arr[largest]) {
			largest = left;
		}
		
		if(right < size && arr[right] > arr[largest]) {
			largest = right;
		}
		
		// Swap if any child is larger than parent, then heapify
		if(largest != start) {
			int temp = arr[largest];
			arr[largest] = arr[start];
			arr[start] = temp;
			
			heapify(arr, size, largest);
		}
		
		
		
	}
	
}
