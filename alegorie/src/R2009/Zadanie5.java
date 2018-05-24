package R2009;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/* Para Słów zadanie 312 */
public class Zadanie5 {
	P[] data;

	public static void main(String[] args) {
		Zadanie5 zad = new Zadanie5();
		System.out.println("Zadanie5 312");
		zad.load();
		//zad.printData();
		zad.solveA();
		zad.solveB();
		zad.solveC();
		zad.solveD();
	}

	private void solveA() {
		int n = 0;
		for(P p : data) {
			if(isPalindrom(p.a)) n++;
			if(isPalindrom(p.b)) n++;
		}
		System.out.println("A. Palindromów: " + n);
	}

	private void solveB() {
		int n = 0;
		for(P p: data) {
			if(isIncluded(p.a, p.b)) n++;
		}
		System.out.println("B. Zawierające się w sobie: " + n);
	}

	private void solveC() {
		int n = 0;
		for(P p: data) {
			if(!isIncluded(p.a, p.b)) {
				if(p.a.charAt(0) != p.b.charAt(p.b.length()-1)
						&& p.a.charAt(p.a.length() -1) != p.b.charAt(0))
					n++;
			}
		}
		System.out.println("C. Tylko sklejenie: " + n);
	}

	private int sufix(String a, String b) {
		int n = 0;
		int s = a.length() - b.length();
		int i = b.length();
		while(s < a.length()) {
		  if(a.substring(s).equals(b.substring(0, i))) return i;
		  s++;
		  i--;
		}
		
		return 0;
	}

	private int prefix(String a, String b) {
		int n = 0;
		int s = 0;
		int i = b.length();
		while(s < b.length()) {
		  if(b.substring(s).equals(a.substring(0, i))) return i;
		  s++;
		  i--;
		}
		
		return 0;
	}

	private void solveD() {
		int pre, suf;
		for(P p: data) {
			suf = sufix(p.a, p.b);
			//System.out.println(p.a + " "  + p.b + " " + suf);
			pre = prefix(p.a, p.b);
			//System.out.println(p.a + " "  + p.b + " " + pre);
			if(suf > pre) {
				System.out.println(p.a + p.b.substring(suf));
			} else {
				System.out.println(p.b.substring(0,pre)+p.a);
			}
		}
	}


	private void load() {
		Scanner sc = null;
		int counter = 0;
		try {
			sc = new Scanner(new File("dane/R2009/dane.txt"));
			while (sc.hasNextLine()) {
				sc.nextLine();
				counter++;
			}
		} catch (IOException e) {
			System.out.println("brak pliku");
		} finally {
			if (sc != null)
				sc.close();
		}
		data = new P[counter];
		//System.out.println("size of data " + counter);
		int i = 0;
		try {
			sc = new Scanner(new File("dane/R2009/dane.txt"));
			P p;
			while (sc.hasNext()) {
				p = new P();
				p.a = sc.next();
				p.b = sc.next();
				data[i++] = p;
			}
		} catch (IOException e) {
			System.out.println("brak pliku");
		} finally {
			if (sc != null)
				sc.close();
		}
	}
	private boolean isPalindrom(String str) {
		int size = str.length()/2;
		int b = 0, end = str.length() -1;
		while(b < size) {
			if(str.charAt(b++) != str.charAt(end--))
				return false;
		}
		return true;
	}
	
	private boolean isIncluded(String s1, String s2) {
		return s1.contains(s2);
	}
	
	private void printData() {
		for(int i = 0; i < data.length; i++)
			System.out.println(i + ": " + data[i].a + " " + data[i].b);
	}
}


class P {
	public String a;
	public String b;
}
