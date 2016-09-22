package com.ld;

import java.util.stream.IntStream;

public class IntStreamDemo {
	
	public static void main(String[] args) {
		
		IntStream
			.range(0, 10)
			.forEach(IntStreamDemo::process);	
		
	}
	
	public static void process(int n) {
		System.out.println(n);
	}
	
}
