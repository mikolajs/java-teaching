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

"/home/administrator/Programy/java-teaching/alegorie/dane/R2010/anagram.txt"
 /home/administrator/Programy/java-teaching/alegorie/dane/R2011/liczby.txt
 
 */

    public class Main {

    	String[] dane;
    	public static void main(String[] args) {
    		 Main z = new Main();
    		System.out.println("zadanko: ");
    		z.load();
    		z.A();
    		z.B();
    		z.C();
    		
    }
    	private void A() {
    		int przez2 = 0;
    		for(String s : dane) {
    			if(s.charAt(s.length()-1)=='0')
    				przez2++;
    		}
    		System.out.println("A) podzielne przez 2: "+ przez2);
    	}
    	private void B() {
    		String max = dane[0];
    		for(String s : dane) {
    			if(s.length() >= max.length()) {
    				max = s;
    			}else if(s.length() == max.length()) {
    				
    				if(s.compareTo(max) > 0) {
    					max = s;
    				}
    			}
    			}
    		System.out.println("B) liczba: " + max + " 62222");
    	}
    	private void C() {
    		int z = 0;
    		for(int t=0; t<1000;t++) {
    			if(dane[t].length()==9) {
    				z++;
    			}
    		}
    		System.out.println("C) jest ich: " + z);
    	}
    	private void load() {
    		dane = new String[1000];
    		File f = new File("/home/administrator/Programy/java-teaching/alegorie/dane/R2011/liczby.txt");
    		Scanner sc = null;
    		try {
    			int i = 0;
    			sc = new Scanner(f);
    			while(sc.hasNext()) {
    				dane[i++] = sc.next();
    			}
    		}catch (IOException e) {
    			System.out.println("Błąd: " + e);
    			
    		}finally {
    			if(sc != null) sc.close();
    			
    		}
    		
    	}

}
        
