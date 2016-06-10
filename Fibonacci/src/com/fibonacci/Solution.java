package com.fibonacci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        try{
            int count = Integer.parseInt(br.readLine());

            for(int i = 0; i < count; i++){
                BigDecimal num = BigDecimal.valueOf(Long.parseLong(br.readLine()));
                if(num.compareTo(BigDecimal.ZERO) > 0 && isFibo(num))
                    System.out.println("IsFibo");
                else
                    System.out.println("IsNotFibo");
            }
        }catch(IOException e){
            System.out.println("Error reading from buffer");
        }
        
    }
    
    public static boolean isFibo(BigDecimal num){
    	BigDecimal base = num.multiply(num).multiply(BigDecimal.valueOf(5));
        return isPerfectSquare(base.add(BigDecimal.valueOf(4))) || isPerfectSquare(base.subtract(BigDecimal.valueOf(4)));
    }
    
    public static boolean isPerfectSquare(BigDecimal num){
        BigDecimal sqrt = BigDecimal.valueOf(Math.sqrt(num.doubleValue()));
        return sqrt.multiply(sqrt).compareTo(num) == 0;
    }
}