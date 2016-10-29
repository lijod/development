package com.ld;

import java.util.Arrays;

class Mergesort {
	
	
	public static void main(String[] args) {
		
		int[] nums = {3,2,4,1,8,6};
		
		
		mergeSort(nums, 0, nums.length - 1);
		
		System.out.println(Arrays.toString(nums));
		
		
	}
	
	private static void mergeSort(int nums[], int start,  int end) {
		
		if(end <= start) {
			return;
		}
		
		int mid = (start + end) / 2;
		
		mergeSort(nums, start, mid);
		mergeSort(nums, mid + 1, end);
		merge(nums, start, mid + 1, end);
		
		
	}

	private static void merge(int[] nums, int start, int mid, int end) {
		
		int lLen = mid - start;
		int rLen = end - mid + 1;
		
		int[] leftArray = new int[lLen];
		int[] rightArray = new int[rLen];
		System.arraycopy(nums, start, leftArray, 0, lLen);
		System.arraycopy(nums, mid, rightArray, 0, rLen);
		
		int i = 0;
		int j = 0;
		int k = start;
		
		while(i < lLen && j < rLen) {
			
			if(leftArray[i] <= rightArray[j]) {
				nums[k++] = leftArray[i];
				i++;
			} else {
				nums[k++] = rightArray[j];
				j++;
			}
			
		}
		
		while(i < lLen) {
			nums[k++] = leftArray[i++];
		}
		
		while(j < rLen) {
			nums[k++] = rightArray[j++];
		}
		
	}
	
}
