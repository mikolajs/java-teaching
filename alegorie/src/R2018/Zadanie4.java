package R2018;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;



public class Zadanie4 {
	private String[] dane;
	
	public static void main(String[] args) {
		Zadanie4 z = new Zadanie4();
		z.loadFile("przyklad.txt");
		System.out.println(z.dane[0]);
		z.solve1();
		z.solve2();
		z.solve3();
		
		z.loadFile("sygnaly.txt");
		z.solve1();
		z.solve2();
		z.solve3();
	}
	
	
	
	
	private void solve1() {
		String s = "";
		System.out.println("1) ");
		for(int i = 39; i < dane.length; i+= 40) {
			s += dane[i].charAt(9);
		}
		System.out.println(s);
	}




	private void solve2() {
	    System.out.println("2)");
		String word = "";
		int max = 0;
		int t;
		for(String s : dane) {
			t = different(s);
			if(t > max) {
			    max = t;
			    word = s;
			}
		}
		System.out.println(word + " : " + max);
	}

	private int different(String str) {
		int d = 0;
		int[] chars = new int[26];
		for(char c : str.toCharArray()) {
			chars[((int) c) - 65] += 1;
		}
		for(int n : chars)
			if(n > 0) d++;
		return d;
	}


	private void solve3() {
	    System.out.println("3)");
		for(String s : dane) {
		    if(maxDist(s) <= 10)
		        System.out.println(s);
		}
	}  

	private int maxDist(String s) {
	    int max = 0;
	    int min = 27;
	    int t;
	    for(char c : s.toCharArray()) {
	       t = ((int) c) - 65;
	       if(max < t) max = t;
	       if(min > t) min = t;
	    }
	    return max - min;
	}


	private void loadFile(String plik) {
		Scanner sc = null;
		int counter = 0;
		dane = new String[1000];
		try {
			sc = new Scanner(new File("dane/R2018/"+ plik));
			while (sc.hasNext()) {
				if (counter < dane.length) {
					dane[counter++] = sc.next();
				} else
					System.out.println("Za dużo linii??? cennik");
			}
		} catch (IOException e) {
			System.out.println("brak pliku");
		} finally {
			if (sc != null)
				sc.close();
		}
	}

}
