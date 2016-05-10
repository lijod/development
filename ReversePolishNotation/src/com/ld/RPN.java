package com.ld;

import java.util.Stack;

/**
 * 
 * test cases:
 * {'2', '1', '+', '3', '*'} -> ((2 + 1) * 3) -> 9
 * {"4", "13", "5", "/", "+"} -> (4 + (13 / 5)) -> 6
 * 
 */
public class RPN {

	public static void main (String[] args) {

		String[] stringArray = {"2", "1", "+", "3", "*"};
		
		double result = evaluateRPN(stringArray);
		
		System.out.println(result);
		
	}	
	
	private static double evaluateRPN (String[] stringArray) {
		
		if (stringArray == null || stringArray.length < 3) {
			return 0.0;
		}
		
		double result = 0.0;
		double a, b;
		Stack<Double> stack = new Stack<>();
		
		for (String str : stringArray) {
		
			switch (str) {
				case "+":
					a = stack.pop();
					b = stack.pop();
					stack.push(a + b);
					break;
					
				case "-":
					a = stack.pop();
					b = stack.pop();
					stack.push(b - a);
					break;
					
				case "*":
					a = stack.pop();
					b = stack.pop();
					stack.push(a * b);
					break;
					
				case "/":
					a = stack.pop();
					b = stack.pop();
					stack.push(b / a);
					break;
				
				default:
					stack.push(Double.parseDouble(str));
					break;
		
			
			}
		}
		
		result = stack.pop();
		
		return result;
	}

}
