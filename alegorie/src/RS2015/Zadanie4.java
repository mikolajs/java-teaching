package RS2015;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Zadanie4 {
	String[] slowa;
	
	public static void main(String[] args) {
		Zadanie4 z = new Zadanie4();
		z.loadFile();
		z.solve1();
		z.solve2();
		z.solve3();
	}
	
	private void solve1() {
		int n = 0;
		for(String s : slowa) {
			if(zerosNmb(s) > s.length()/2) n++;
		}
		System.out.println("1) więcej zer niż jedynek: " + n);
	}

	private void solve2() {
		int n = 0;
		int begin = 0;
		int end = 0;
		boolean stop = true;
		for(String s : slowa) {
			begin = 0;
			end = s.length()-1;
			if(s.charAt(begin) != '0' || s.charAt(end) != '1') continue;
			stop = true;
			//System.out.println("word " + s);
			while(begin < end) {
				//System.out.println("begin & end " + begin + " " +end);
				stop = true;
				if(s.charAt(begin + 1) == '0') {
					begin++;
					stop = false;
				}
				if(s.charAt(end - 1) == '1') {
					end--;
					stop = false;
				}
				if(stop) break;
			}
			if(end - begin == 1) n++;
		}
		System.out.println("2) z 2 blokami z przodu 0 z tyłu 1: " + n);
	}

	private void solve3() {
		int longest = 0;
		int n = 0;
		for(String s: slowa) {
			n = 0;
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == '0') n++;
				else {
					if(n > longest) longest = n;
					n = 0;
				}
				if(i == s.length() - 1) {
					if(n > longest) longest = n;
				}
			}
		}
		StringBuilder longestSB = new StringBuilder();
		
		System.out.println("2) najdłuży blok ma " + longest);
		for(String s: slowa) {
			n = 0;
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == '0') n++;
				else {
					if(n == longest)  {
						longestSB.append(s + "\n");
					}
					n = 0;
				} 
				if(i == (s.length() - 1) && n == longest) longestSB.append(s + "\n");
				
			}
		}
		
		
		
		System.out.println("I są to: \n" + longestSB.toString());
	}
	
	private int zerosNmb(String s) {
		int z = 0;
		for(char c: s.toCharArray()) {
			if(c == '0') z++;
		}
		return z;
	}

	private void loadFile() {
        Scanner sc = null;
        slowa = new String[1000];
        int counter = 0;
        try {
            sc = new Scanner(new File("dane/RS2015/slowa.txt"));
            while (sc.hasNext()) {
                if (counter < slowa.length) {
                    slowa[counter++] = sc.next();
                } else
                    System.out.println("Za dużo linii???");
            }
            sc.close();
            sc = null;
            
        } catch (IOException e) {
            System.out.println("brak pliku");
        } finally {
            if (sc != null)
                sc.close();
        }
    }

}
