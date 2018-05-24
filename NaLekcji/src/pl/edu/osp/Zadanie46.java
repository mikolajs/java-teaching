package pl.edu.osp;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Zadanie46 {

	String[] dane;
	
	public static void main(String[] args) {
		Zadanie46 z = new Zadanie46();
		z.load();
		System.out.println("Zadanie 46 ");
		z.A();
		z.B();
		z.C();
	}
	
	
	private void A() {
		int wiecej = 0;
		for(String s : dane) {
			if(zera(s) > s.length()/2) 
				wiecej++;
		}
		System.out.println("zadanie 1 : " + wiecej);
	}
	
	private int zera(String str) {
		int z = 0;
		for(char c : str.toCharArray()) {
			if(c == '0') z++;
		}
		return z;
	}


	private void B() {
		int przez2 = 0;
		int przez8 = 0;
		for(String s : dane) {
			if(s.charAt(s.length()-1) == '0') {
				przez2++;
				if(s.charAt(s.length()-2) == '0' &&
						s.charAt(s.length()-3) == '0')
					przez8++;
			}
		}
		System.out.println("zadanie 2");
		System.out.println("podzielne przez 2 : " + przez2);
		System.out.println("podzielne przez 8 : " + przez8);
	}


	private void C() {
		int liniaMin = 0;
		int liniaMax = 0;
		String max = dane[0];
		String min = dane[0];
		int linia = 0;
		for(String s : dane) {
			linia++;
			if(s.length() > max.length()) {
				max = s;
				liniaMax = linia;
			} else if(s.length() == max.length()) {
				if(s.compareTo(max) > 0) {
					max = s;
					liniaMax = linia;
				}
			}
			if(s.length() < min.length()) {
				min = s;
				liniaMin = linia;
			} else if(s.length() == min.length()) {
				if(s.compareTo(min) < 0) {
					min = s;
					liniaMin = linia;
				}
			}
			
		}
		System.out.println("zadanie 3");
		System.out.println("Max linia: " + liniaMax + " liczba " + max);
		System.out.println("Min linia: " + liniaMin + " liczba " + min);
	}


	public void load() {
		dane = new String[1000];
		File f = new File("/home/administrator/Programy/java-teaching/alegorie/dane/R2015/liczby.txt");
		Scanner sc = null;
		try {
			int i = 0;
			sc = new Scanner(f);
			while(sc.hasNext()) {
				dane[i++] = sc.next();
			}
		} catch (IOException e) {
			System.out.println("Błąd: " + e);
		} finally {
			if(sc != null) sc.close();
		}
	}

}
