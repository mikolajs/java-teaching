package R2011;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Zadanie6 {
	
	String[] data;
	
	public static void main(String[] args) {
		Zadanie6 zad = new Zadanie6();
		zad.loadFile();
		zad.solveA();
		zad.solveB();
		zad.solveC();
	}
	
	private void solveA() {
		int even = 0;
		for(String bin : data) {
			if(bin.charAt(bin.length()-1) == '0') even++;
		}
		System.out.println("A) Parzystych: " + even);
	}

	private void solveB() {
		int max = Integer.MIN_VALUE;
		int tmp;
		for(String bin : data) {
			tmp = toDec(bin);
			if(max < tmp) max = tmp;
		}
		System.out.println("B) Największa liczba: " + toBin(max) + " " + max);
	}

	private void solveC() {
		int sum = 0;
		int n = 0;
		for(String bin : data) {
			if(bin.length() == 9) {
				sum += toDec(bin);
				n++;
			}
		}
		System.out.println("C) 9 cyfr ma " + n + 
				" liczb, a ich suma dwójkowo to " + 
				toBin(sum));
	}

	private void loadFile() {
		Scanner sc = null;
		data = new String[1000];
		int counter = 0;
		try {
			sc = new Scanner(new File("dane/R2011/liczby.txt"));
			while(sc.hasNext()) {
				if(counter < data.length) {
				  data[counter++] = sc.nextLine().trim();	
				} else System.out.println("Za dużo linii???");
			}
		} catch (IOException e) {
			System.out.println("brak pliku");
		} finally {if(sc != null) sc.close(); }
	}
	
	private String toBin(int dec) {
		return Integer.toBinaryString(dec);
	}
	
	private int toDec(String bin) {
		return Integer.valueOf(bin, 2);
	}
	
	private int plainToDec(String bin) {
		int dec = 0;
		int p = 1;
		for(int i = bin.length()-1; i >= 0; i--) {
			dec += ((int)bin.charAt(i) - 48)*p;
			p *= 2;
		}
		return dec;
	}
	
}
