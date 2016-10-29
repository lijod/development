package com.ld;

import java.util.Arrays;

public class QuickSort {
	
	public static void main(String[] args) {
		
		int[] arr = {5,3,4,1,6};
		
		sort(arr, 0, arr.length - 1);
		
		System.out.println(Arrays.toString(arr));
	}

	
	
	private static void sort(int[] arr, int left, int right) {
		
		if(left < right) {
			
			int partition = getPartition(arr, left, right);

			sort(arr, left, partition - 1);
			sort(arr, partition + 1, right);
			
		}
		
	}



	private static int getPartition(int[] arr, int left, int right) {
		
		int pivot = arr[right];
		
		int i = left;
		
		for(int j = left; j < right; j++) {
			
			if(arr[j] < pivot) {
				
				if(i != j) {
					int temp = arr[i];
					arr[i] = j;
					arr[j] = temp;
				} 
				
				i++;
			}
			
		} 	
		
		int temp = arr[i];
		arr[i] = arr[right];
		arr[right] = temp;
		
		return i;
	}
	
	
	

}
