package R2017;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/* PIKSELE */
public class Zadanie6 {
	int[] data;
	
	public static void main(String[] args) {
		Zadanie6 zad = new Zadanie6();
		zad.loadFileExample();
		System.out.println("DLA DANYCH TESTOWYCH");
		zad.solve1();
		System.out.println("Powinno być: 255 0");
		zad.solve2();
		System.out.println("Powinno być: 3");
		zad.solve3();
		System.out.println("Powinno być: 5");
		zad.solve4();
		System.out.println("Powinno być: 198");
		zad.loadFile();
		System.out.println("DLA DANYCH TESTOWYCH");
		zad.solve1();
		zad.solve2();
		zad.solve3();
		zad.solve4();
	}
	
	
	private void solve1() {
		int max = data[0];
		int min = data[0];
		for(int p : data) {
			if(max < p) max = p;
			else if(min > p) min = p;
		}
		System.out.println("1) " + max + " " + min);
	}


	private void solve2() {
		// TODO Auto-generated method stub
		
	}


	private void solve3() {
		// TODO Auto-generated method stub
		
	}


	private void solve4() {
		// TODO Auto-generated method stub
		
	}


	private void loadFile() {
		Scanner sc = null;
		data = new int[200*320];
		int counter = 0;
		try {
			sc = new Scanner(new File("dane/R2017/dane.txt"));
			while (sc.hasNextInt()) {
				if (counter < data.length) {
					data[counter++] = sc.nextInt();
				} else
					System.out.println("Za dużo linii???");
			}
		} catch (IOException e) {
			System.out.println("brak pliku");
		} finally {
			if (sc != null)
				sc.close();
		}
	}
	
	private int[] loadFileExample() {
		Scanner sc = null;
		data = new int[200*320];
		int counter = 0;
		try {
			sc = new Scanner(new File("dane/R2017/przyklad.txt"));
			while (sc.hasNextInt()) {
				if (counter < data.length) {
					data[counter++] = sc.nextInt();
				} else
					System.out.println("Za dużo linii???");
			}
		} catch (IOException e) {
			System.out.println("brak pliku");
		} finally {
			if (sc != null)
				sc.close();
		}
		return data;
	}
	
}
