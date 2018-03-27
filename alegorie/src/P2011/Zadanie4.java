package P2011;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/* zad 294 */
public class Zadanie4 {
	private String[] data;

	public static void main(String[] args) {
		Zadanie4 zad = new Zadanie4();
		zad.solveA();
		zad.solveB();
		zad.solveC();
	}
	
	public Zadanie4() {
		loadFile();
	}
	
	private void solveA() {
		int odd = 0;
		int even = 0;
		System.out.println("ODP. A) Parzyste i nieparzyste:");
		for(String s: data) {
			if(s.length() % 2 == 0) even++;
			else odd++;
		}	
		System.out.println("Parzystych: " + even + " nieparzystych: " + odd);
	}
	
	private void solveB() {
		System.out.println("ODP. B) Palindromy:");
		for(String s: data) {
			if(isPalindrom(s)) {
				System.out.println(s);
			}
		}
	}
	
	private void solveC() {
		System.out.println("ODP. C) suma kolejnych liter == 220 :");
		for(String s: data) {
			for(int i = 1; i < s.length(); i++) {
				if((int)s.charAt(i-1) + (int)s.charAt(i) == 220) {
					System.out.println(s);
					break;
				}
			}
		}
	}
	
private void loadFile() {
		Scanner sc = null;
		data = new String[200];
		int counter = 0;
		try {
			sc = new Scanner(new File("dane/P2011/hasla.txt"));
			while(sc.hasNext()) {
				if(counter < data.length) {
				  data[counter++] = sc.nextLine().trim();	
				} else System.out.println("Za dużo linii???");
			}
		} catch (IOException e) {
			System.out.println("brak pliku");
		} finally {if(sc != null) sc.close(); }
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

}
