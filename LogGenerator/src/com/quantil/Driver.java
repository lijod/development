package com.quantil;

import java.text.ParseException;

/**
 * Driver class calls the class specified in the command line and passes 
 * the input path to it.
 * @author lijodaniel
 *
 */
public class Driver {
	public static void main(String[] args) throws ParseException {		
		
		if(args.length != 2) {
			System.out.println("Invalid input, cannot run application");
			return;
		}
		
		switch (args[0]) {
			case "Generator":
				Generator generator = new Generator(args[1]);
				generator.generate();
				break;
			case "QueryProcessor":
				QueryProcessor processor = new QueryProcessor(args[1]);
				processor.loadProcessor();
				break;
			default:
				System.out.println("Invalid input, " + args[0]);
				break;
		}
		
	}
}
