package com.ld;

public class RotateArray {

	public static void main(String[] args) {
		int[] numArray = {1,2,3,4,5,6,7};
		int rotateBy = 3;
		
		rotateArray(numArray, rotateBy);
	}
	
	private static void rotateArray(int[] numArray, int rotateBy) {
		if (rotateBy > numArray.length) {
			rotateBy = rotateBy % numArray.length;
		}
		
		
		
	}
}
