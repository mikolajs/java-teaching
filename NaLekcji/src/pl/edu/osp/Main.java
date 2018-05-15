package pl.edu.osp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
Parzystych napisów jest 504
Napisów o tej samej liczbie zer i jedynek jest 110
Napisów z samymi zerami: 32 i samymi zerami: 50
Długość napisów: 
2 : 43
3 : 38
4 : 37
5 : 57
6 : 53
7 : 68
8 : 78
9 : 103
10 : 83
11 : 90
12 : 86
13 : 81
14 : 68
15 : 59
16 : 56


"/home/administrator/Programy/java-teaching/alegorie/dane/P2013/napisy.txt"
 */

    public class Main {

    	
    	public static int check(String binar) {
    		int zero = 0;
    		int one = 0;
    		for(int i=0;i<binar.length();i++) {
    			if(binar.charAt(i) == 48)
    				zero++;
    			else
    				one++;
    		}
    		if(zero == one)
    			return 2;
    		else if(zero == 0 && one > 0)
    			return 1;
    		else if(one == 0 && zero > 0)
    			return 0;
    		return -1;
    			
    	}
    	
    	@SuppressWarnings({ "resource" })
    	public static void main (String[] args) throws IOException{
    		BufferedReader br = null;
    		FileReader fr = null;
    		String read = "";
    		int even = 0;
    		int equal = 0;
    		int ones = 0;
    		int zeros = 0;
    		int x;
    		int[] tab = new int[15];
    		fr = new FileReader("/home/administrator/Programy/java-teaching/alegorie/dane/P2013/napisy.txt");
    		br = new BufferedReader(fr);
    		for(int i=0;i<1000;i++) {
    			read = br.readLine();
    			x = check(read);
    			if(x == 2)
    				equal++;
    			else if(x == 1)
    				ones++;
    			else if(x == 0)
    				zeros++;
    			for(int j=0;j<15;j++) {
    				if(read.length() == j+2) {
    					tab[j]++;
    					break;
    				}
    			}
    		}
    		for(int i=0;i<15;i+=2)
    			even += tab[i];
    		System.out.println(even);
    		System.out.println(equal);
    		System.out.println(zeros + " " + ones);
    		for(int i=0;i<15;i++) {
    			System.out.print(tab[i]);
    			if(i != 14)
    				System.out.print(" ");
    		}
    	}

}
        
