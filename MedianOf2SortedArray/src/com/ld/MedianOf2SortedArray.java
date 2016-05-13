package com.ld;

import java.util.Arrays;

// O(log n)
// Both arrays should be of same length
public class MedianOf2SortedArray {

	public static void main(String[] args) {
		int[] firstArr = {1,2,3};
		int[] secondArr = {4,5,6,7,8,9,10,11};
		
		System.out.println("Using divide & conquer O(log n):::: " + findMedianOf2(firstArr, secondArr));
		
		System.out.println("Using merge O(n):::: " + findMedianOf2UsingMerge(firstArr, secondArr));

		System.out.println("Programcreek O(log n)::::" + findMedianSortedArrays(firstArr, secondArr));
	}

	private static float findMedianOf2(int[] firstArr, int[] secondArr) {
			
		int arrLength = firstArr.length;
		
		if (arrLength == 0) {
			return 0;
		}
		
		if(arrLength == 1) {
			return ((float)(firstArr[0] + secondArr[0])) / 2;
		}
		
		if (arrLength == 2) {
			return ((float)(Math.max(firstArr[0], secondArr[0]) +  Math.min(firstArr[1], secondArr[1])) / 2);
		}
		
		
		float firstMed = findMedian(firstArr); 
		float secondMed = findMedian(secondArr); 

		int midIndex = arrLength / 2;
		
		if (firstMed < secondMed) {
			return findMedianOf2(Arrays.copyOfRange(firstArr, midIndex, arrLength), Arrays.copyOfRange(secondArr, 0, midIndex + 1));
		} else if (firstMed > secondMed) {
			return findMedianOf2(Arrays.copyOfRange(firstArr, 0, midIndex + 1), Arrays.copyOfRange(secondArr, midIndex, arrLength));
		} else {
			return firstMed;
		} 		
	}

	private static float findMedian(int[] arr) {
		int midIndex = arr.length / 2;
		if (arr.length % 2 == 0) {
			return ((float)(arr[midIndex - 1] + arr[midIndex])) / 2;
		} else {
			return arr[midIndex];
		}
	}
	
	private static float findMedianOf2UsingMerge (int[] firstArr, int[] secondArr) {
		int firstLength = firstArr.length;
		int secondLength = secondArr.length; 
		int firstCounter = 0;
		int secondCounter = 0;
		int mergeCounter = 0;
		int[] mergedArray = new int[firstLength + secondLength];
		while (true) {
			
			if (firstCounter >= firstLength && secondCounter >= secondLength) {
				break;
			} else if (firstCounter >= firstLength) {
				mergedArray[mergeCounter++] = secondArr[secondCounter++];
				continue;
			} else if (secondCounter >= secondLength) {
				mergedArray[mergeCounter++] = firstArr[firstCounter++];
				continue;
			}
			
			if (firstArr[firstCounter] < secondArr[secondCounter]) {
				mergedArray[mergeCounter++] = firstArr[firstCounter++];
			} else if (firstArr[firstCounter] > secondArr[secondCounter]) {
				mergedArray[mergeCounter++] = secondArr[secondCounter++];
			} else {
				mergedArray[mergeCounter++] = firstArr[firstCounter++];
				mergedArray[mergeCounter++] = secondArr[secondCounter++];
			}
			
		}
		
		int midIndex = mergeCounter / 2;
		if (mergeCounter % 2 == 0) {
			return (mergedArray[midIndex] + mergedArray[midIndex -1]) / 2;
		} else {
			return mergedArray[midIndex];
		}
	}
	
	
	//=== Copied from program creek
	
	public static double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
	 
		if ((m + n) % 2 != 0) // odd
			return (double) findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1);
		else { // even
			return (findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1) 
				+ findKth(A, B, (m + n) / 2 - 1, 0, m - 1, 0, n - 1)) * 0.5;
		}
	}
	 
	public static int findKth(int A[], int B[], int k, 
		int aStart, int aEnd, int bStart, int bEnd) {
	 
		int aLen = aEnd - aStart + 1;
		int bLen = bEnd - bStart + 1;
	 
		// Handle special cases
		if (aLen == 0)
			return B[bStart + k];
		if (bLen == 0)
			return A[aStart + k];
		if (k == 0)
			return A[aStart] < B[bStart] ? A[aStart] : B[bStart];
	 
		int aMid = aLen * k / (aLen + bLen); // a's middle count
		int bMid = k - aMid - 1; // b's middle count
	 
		// make aMid and bMid to be array index
		aMid = aMid + aStart;
		bMid = bMid + bStart;
	 
		if (A[aMid] > B[bMid]) {
			k = k - (bMid - bStart + 1);
			aEnd = aMid;
			bStart = bMid + 1;
		} else {
			k = k - (aMid - aStart + 1);
			bEnd = bMid;
			aStart = aMid + 1;
		}
	 
		return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
	}
}
