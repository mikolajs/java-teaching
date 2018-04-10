package pl.edu.osp;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Zadanie294 {
	String[] hasla;
	
	public static void main(String[] args) {

		Zadanie294 zad = new Zadanie294();
		System.out.println("zadanie 294");
		zad.load();
		zad.A();
		zad.B();
		zad.C();
	}

	private void A() {
		int parzyste = 0;
		for(String s : hasla) {
			if(s.length() % 2 == 0) parzyste++;
		}
		System.out.println("Odp A. Parzystych " + parzyste +
				" nieparzyste " + (hasla.length - parzyste));
	}

	private void B() {
		System.out.println("Odp B. Palindromami są: " );
		for(String s : hasla) {
			if(palindrom(s)) {
				System.out.println(s);
			}
		}
	}

	private void C() {
		System.out.println("Odp C. Kolejne litery o sumie 220: " );
		for(String s : hasla) {
			if(suma220(s)) {
				System.out.println(s);
			}
		}
	}

	private void load() {
		hasla = new String[200];
		File file = new File("/home/administrator/Dropbox/school/LO/matury informatyka/2011/Dane_PP/hasla.txt");
		Scanner sc = null;
		int licznik = 0;
		try {
			sc = new Scanner(file);
			while(sc.hasNext()) {
				hasla[licznik++] = sc.next().trim();
			}
		} catch(IOException e) {
			System.out.println("Błąd pliku");
		} finally {
			if(sc != null) sc.close();
		}
	}
	
	private boolean suma220(String s) {
		int tmp;
		for(int i = 1; i < s.length(); i++) {
			tmp = (int) s.charAt(i-1) + (int) s.charAt(i);
			if(tmp == 220) return true;
		}
		return false;
	}
	
	private boolean palindrom(String s) {
		for(int i = 0; i < s.length()/2;i++) {
			if(s.charAt(i) != s.charAt(s.length()-i -1))
				return false;
		}
		return true;
	}
}
