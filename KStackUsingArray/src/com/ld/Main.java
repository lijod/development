package com.ld;

public class Main {

	public static void main(String[] args) {

		KStackUsingArray stacks = new KStackUsingArray(3, 10);
		
		System.out.println(stacks);
		
		stacks.push(5, 0);
		System.out.println(stacks);
		stacks.push(6, 0);
		System.out.println(stacks);
		stacks.push(8, 1);
		System.out.println(stacks);
		
		
		System.out.println(stacks.pop(0));
		System.out.println(stacks);
		
//		System.out.println(stacks.pop(0));
//		System.out.println(stacks);
//		
//		System.out.println(stacks.pop(1));
//		System.out.println(stacks);
		
		stacks.push(7, 0);
		System.out.println(stacks);
		
		stacks.push(10, 0);
		System.out.println(stacks);
		
		stacks.push(11, 0);
		System.out.println(stacks);
		
		stacks.push(12, 0);
		System.out.println(stacks);
		
	}

}
