package RS2018;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class Zadanie6 {
	DWord[] data;
	public static void main(String[] args) {
		Zadanie6 z = new Zadanie6();
		z.loadFile();
		//z.print();
		z.solve1();
		z.solve2();
		z.solve3();
	}
	
	private void solve1() {
		System.out.println("1) kończy się na A:  ");
		int n = 0;
		for(DWord w : data) {
		    if(w.a.charAt(w.a.length()-1) == 'A')
		        n++;
		    if(w.b.charAt(w.b.length()-1) == 'A')
                n++;
		}
		System.out.println(n);
	}

	private void solve2() {
	    System.out.println("2) słowo a w słowie b:  ");
        int n = 0;
        for(DWord w : data) {
            if(w.a.length() <= w.b.length()) {
                if(isIn(w.a, w.b))
                    n++;
            }
        }
        System.out.println(n);
	}
	
	private boolean isIn(String s1, String s2) {
	    //System.out.println("Start " + s1 +" " + s2);
	    for(int i = 0; i <= s2.length() - s1.length(); i++) {
	        
	        if(s2.substring(i, i+s1.length()).equals(s1) ) {
	           // System.out.println("+ " + s2.substring(i, i+s1.length()) + " " + s1);
	            return true;
	        }
	    }
	    return false;
	}
	
	private void solve3() {
	    int n = 0;
	    System.out.println("3) anagramy ");
	    for(DWord w : data) {
	        if(w.a.length() == w.b.length()) {
	            if(anagram(w.a, w.b)) {
	                w.print();
	                n++;
	            }
	        }
	    }
	    System.out.println("ilość " +n );
	}
	
	private boolean anagram(String s1, String s2) {
		int[] t1 = new int[26];
		int[] t2 = new int[26];
		for(char c : s1.toCharArray())
			t1[(int)c - 65] += 1;
		for(char c : s2.toCharArray())
			t2[(int)c - 65] += 1;
	    return Arrays.equals(t1, t2);
	}
	
	
	private void loadFile() {
        Scanner sc = null;
        data = new DWord[1000];
        int counter = 0;
        try {
            sc = new Scanner(new File("dane/RS2018/slowa.txt"));
            while (sc.hasNext()) {
            	data[counter] = new DWord();
            	data[counter].a = sc.next();
            	data[counter].b = sc.next();
            	counter++;
            }
        } catch (IOException e) {
            System.out.println("brak pliku");
        } finally {
            if (sc != null)
                sc.close();
        }
    }
	
	private void print() {
	    for(DWord w : data) 
            System.out.println(w.a + " " + w.b);
	}
}

class DWord {
	public String a;
	public String b;
	public void print() {
	    System.out.println(a + " " + b);
	}
}
