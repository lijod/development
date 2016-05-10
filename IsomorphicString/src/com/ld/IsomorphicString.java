package com.ld;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicString {
	
	public static void main (String[] args) {
		
		String s = "add";
		String t = "egg";
		
		System.out.println(isIsomorphic(s, t));
	}
	
	private static boolean isIsomorphic (String s, String t) {
		if (s == null || t == null) {
			return false;
		}
		
		if (s.length() != t.length()) {
			return false;
		}
		
		Map<Character, Character> charMap = new HashMap<>();
		
		for (int i = 0; i < s.length(); i++) {
			Character c1 = s.charAt(i);
			Character c2 = s.charAt(i);
			if (charMap.containsKey(c1)) {
				if (c2.equals(charMap.get(c1))) {
					continue;
				} else {
					return false;
				}
			} else {
				// Already mapped with other character
				if (charMap.containsValue(c2)) {
					return false;
				}
				
				charMap.put(c1, c2);
			}
 		}
		
		return true;
	}
	
}
