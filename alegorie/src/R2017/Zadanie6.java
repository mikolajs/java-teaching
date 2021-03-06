package R2017;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/* PIKSELE  zadanie 301*/
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
		System.out.println("DLA DANYCH ZADANIA");
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
		int nonSym = 0;
		int j;
		for(int i = 0; i < data.length; i += 320) {
			//System.out.println(i);
			j = 0;
			while(j < 160) {
				if(data[i + j] != data[i + 319 - j] ) {
					nonSym++;
					break;
				}
				j++;
			}
		}
		System.out.println("2) " + nonSym);
	}


	private void solve3() {
		int contrast = 0;
		int k = 0;
		for(int i = 0; i < data.length; i += 320) {
			for(int j = 0; j < 320; j++) {
				k = i + j;
				//górny piksel
				if(k > 320 && Math.abs(data[k - 320] - data[k]) > 128) {
					contrast++;
					continue;
				}
				//dolny piksel
				if(k < 63680 && Math.abs(data[k+320] - data[k]) > 128) {
					contrast++;
					continue;
				}
				//lewy piksel
				if(k % 320 != 0 && Math.abs(data[k - 1] - data[k]) > 128) {
					contrast++;
					continue;
				}
				//prawy piksel
				if(k % 320 != 319 && Math.abs(data[k+1] - data[k]) > 128) {
					contrast++;
					continue;
				}
			}
		}
		System.out.println("3) " + contrast);
	}


	private void solve4() {
		int max = 0;
		int kolor = 0;
		int colMax = 0;
		int nr = 0;
		int k;
		for(int i = 0; i < 320; i++) {
			nr = 1;
			kolor = -1;
			for(int j = 0; j < data.length; j += 320) {
				k = j + i;
				if(kolor == data[k]) {
					nr++;
					if(k > 63679 && colMax < nr) colMax = nr; 
				} else {
					if(colMax < nr) colMax = nr;
					nr = 1;
					kolor = data[k];
				}
				
				
			}
			if(max < colMax) max = colMax;
		}
		System.out.println("4) " + max);
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
