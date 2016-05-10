package com.ld;

public class RotateArray {

	public static void main(String[] args) {
		int[] numArray = {0, 1, 2, 3, 4, 5, 6};
		int rotateBy = 8;
		
		rotateArray(numArray, rotateBy);
		
		for(int num : numArray) {
			System.out.print(num + " ");
		}
	}
	
	private static void rotateArray(int[] numArray, int rotateBy) {
		if (rotateBy > numArray.length) {
			rotateBy = rotateBy % numArray.length;
		}
		
		reversePartOfArray(numArray, numArray.length - rotateBy, numArray.length - 1);
		reversePartOfArray(numArray, 0, numArray.length - rotateBy - 1);
		reversePartOfArray(numArray, 0, numArray.length - 1);
		
	}
	
	private static void reversePartOfArray(int[] numArray, int left, int right) {
		
		while(left < right) {
			int temp = numArray[left];
			numArray[left] = numArray[right];
			numArray[right] = temp;
			left++;
			right--;
		}
		
	}
}
