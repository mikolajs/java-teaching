package P2014;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/* zadanie 296 */
public class Zadanie4 {
	private Couple[] data;
	public static void main(String[] args) {

		Zadanie4 zad = new Zadanie4();
		zad.test();
		zad.solveA();
		zad.solveB();
		zad.solveC();
	}
	
	private void solveA() {
System.out.println("A) Wielokrotności: ");
		int n = 0;
		for(Couple c : data) {
			if(c.a > c.b) {
				if(c.a % c.b == 0) n++;
			} else {
				if(c.b % c.a == 0) n++;
			}
		}
	 System.out.println("Jest wielokrotnością: " + n);
	}

	private void solveB() {
		System.out.println("B) Gdzie NWD liczb jest 1: ");
		int n = 0;
		for(Couple c : data) {
			if(gdc(c.a, c.b) == 1) n++; 
		}
	 System.out.println("NWD równe jeden jest:  " + n);
		
	}

	private void solveC() {
		System.out.println("C) Równe sumy cyfr : ");
		int n = 0;
		for(Couple c : data) {
			if(sumOfCipher(c.a) == sumOfCipher(c.b)) n++; 
		}
	 System.out.println("Liczb o tej samej sumie cyfr:  " + n);
		
	}

	public Zadanie4() {
		loadFile();
	}
	
	private int gdc(int a, int b) {
		int tmp;
		if(b > a) {
			tmp = b;
			b = a;
			a = tmp;
		}
		while(b != 0) {
			tmp = a % b;
			a = b;
			b = tmp;
		}
		return a;
	}
	
	private int sumOfCipher(int number) {
		int sum = 0;
		while(number > 0) {
			sum += number % 10;
			number /= 10;
		}
		return sum;
	}
	
	
	private void loadFile() {
		Scanner sc = null;
		data = new Couple[1000];
		int counter = 0;
		try {
			sc = new Scanner(new File("dane/P2014/PARY_LICZB.TXT"));
			while (sc.hasNextInt()) {
				if (counter < data.length) {
					data[counter] = new Couple();
					data[counter].a = sc.nextInt();
					data[counter].b = sc.nextInt();
					counter++;
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
	
	private void test() {
		assert gdc(12, 8) == 4;
		assert gdc(13, 7) == 1;
		assert gdc(81, 36) == 9;
	}
}

class Couple {
	public int a;
	public int b;
}
