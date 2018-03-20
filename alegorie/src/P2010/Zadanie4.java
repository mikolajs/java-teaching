package P2010;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Zadanie4 {
	
	String[] data;

	public static void main(String[] args) {
		Zadanie4 zad = new Zadanie4();
	}
	
	public Zadanie4() {
		loadFile();
		test();
	}
	
	public void solve() {
		
	}
	
	private void loadFile() {
		Scanner sc = null;
		data = new String[1000];
		int counter = 0;
		try {
			sc = new Scanner(new File("dane/P2010/dane.txt"));
			while(sc.hasNextInt()) {
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
	
	private void test() {
		assert isPalindrom("JABFDFBAJ") == true;
		assert isPalindrom("HAJAHAJAH") == true;
		assert isPalindrom("ABBA") == true;
		assert isPalindrom("JANA") == false;
	}

}
