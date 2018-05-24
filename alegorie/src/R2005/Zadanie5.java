package R2005;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Zadanie5 {
	int[] dane;
	public static void main(String[] args) {
		Zadanie5 z = new Zadanie5();
		System.out.println("A. Najlepsza suma to 8");
		z.solveB();
		z.solveC();
	}
	
	public void solveB() {
		System.out.println("B. ");
		load("dane/R2005/dane5-1.txt");
		System.out.println("W pliku 1 najlepsza suma: " +
	bestSum(dane));
		load("dane/R2005/dane5-2.txt");
		System.out.println("W pliku 2 najlepsza suma: " +
		bestSum(dane));
		load("dane/R2005/dane5-3.txt");
		System.out.println("W pliku 3 najlepsza suma: " +
		bestSum(dane));
	}
	
	private int bestSum(int[] d) {
		int best = 0;
		int sum = 0;
		
		for(int i = 0; i < d.length; i++) {
			sum = 0;
			for(int j = i; j < d.length; j++) {
				sum += d[j];
				if(best < sum) best = sum;
			}
		}
		return best;
	}
	
	public void solveC() {
		System.out.println("C. dla plików 1 i 2 możliwe jest kilka wyników");
		load("dane/R2005/dane5-1.txt");
		System.out.println("W pliku 1 najpopularniejszy: " +
		mostPopular(dane));
		load("dane/R2005/dane5-2.txt");
		System.out.println("W pliku 2 najpopularniejszy: " +
		mostPopular(dane));
		load("dane/R2005/dane5-3.txt");
		System.out.println("W pliku 3 najpopularniejszy: " +
		mostPopular(dane));
	}
	
	private int mostPopular(int[] d) {
		final int vector = 50; 
		int[] popularity = new int[100];
		for(int n : d) {
			popularity[n+vector]++;
		}
		int max = -10000;
		int maxNr = -10000;
		for(int i = 0; i < popularity.length; i++) {
			if(max < popularity[i]) {
				max = popularity[i];
				maxNr = i-vector;
			}
		}
		return maxNr;
	}
	
	private void load(String path) {
		File file = new File(path);	

		Scanner sc = null;
		int licznik = 0;
		int linii = 0;
		try {
			sc = new Scanner(file);
			while(sc.hasNextInt()) {
				sc.nextInt();
				linii++;
			}
		} catch(IOException e) {
			System.out.println("Błąd pliku");
		} finally {
			if(sc != null) sc.close();
		}
		
		dane = new int[linii];
		try {
			sc = new Scanner(file);
			while(sc.hasNextInt()) {
				dane[licznik++] = sc.nextInt();
			}
		} catch(IOException e) {
			System.out.println("Błąd pliku");
		} finally {
			if(sc != null) sc.close();
		}
	}
}
