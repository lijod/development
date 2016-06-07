package com.ld;

import java.lang.reflect.Array;

public class StackUsingArray {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<>(10);
		
		stack.push("A");
		stack.push("B");
		
		
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
	}

}

class Stack<E> {
	
	private int size;
	private int top;
	private E[] stackArray;
	
	public Stack(int size) {
		this.size = size;
		this.top = -1;
		stackArray = (E[]) new Object[size];
	}
	
	public E pop() {
		if(this.top < 0) {
			return null;
		}
		E toReturn = stackArray[this.top];
		stackArray[this.top--] = null;
		
		return toReturn;
	}
	
	public void push(E e) throws ArrayIndexOutOfBoundsException{
		if(this.top >= this.size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		stackArray[++this.top] = e;
	}
}