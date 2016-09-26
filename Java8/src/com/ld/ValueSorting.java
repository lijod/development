package com.ld;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ValueSorting {

	public static void main(String[] args) {
		
		java.util.Map<String, Integer> map = new HashMap<>();
		
		map.put("Sam", 5);
		map.put("Jam", 7);
		map.put("Tam", 3);
		map.put("Ham", 4);
		map.put("Lam", 1);
		map.put("Mam", 2);
		
//		List<Entry<String, Integer>> set = map.entrySet()
		java.util.Map<String, Integer> set = map.entrySet()
			.stream()
//			.sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
			.sorted(Entry.<String, Integer>comparingByValue().reversed())
//			.collect(Collectors.toList());
			.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		System.out.println(set);
		
	}

}
