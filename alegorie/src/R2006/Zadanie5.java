package R2006;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/* Słowa zadanie 316 */
public class Zadanie5 {
	String[] data;
	public static void main(String[] args) {
		Zadanie5 zad = new Zadanie5();
		System.out.println("Zadanie5 (316)");
		zad.load();
		zad.solveA();
		zad.solveB();
	}
	
	private void solveA() {
		Arrays.sort(data, Comparator.comparing(String::toString));
		String last = "";
		int n = 0;
		int many = 0;
		int maxMany = 0;
		String maxString = "";
		for(String s : data) {
			//System.out.println(s);
			if(last.equals(s)) n++;
			else {
				if(n > 0) {
					n = 0;
					many++;
					if(maxMany < n) {
						maxMany = n;
						maxString = s;
					}
				}
				last = s;
			}
		}
		System.out.println("Ilość wielokrotnych: " + many);
		
	}

	private void solveB() {
		// TODO Auto-generated method stub
		
	}

	private void load() {
		Scanner sc = null;
		int counter = 0;
		try {
			sc = new Scanner(new File("dane/R2006/dane.txt"));
			while(sc.hasNextLine()) {
				sc.nextLine();
				counter++;
			}
		} catch(IOException e) {
			System.out.println("brak pliku");
		} finally {
			if(sc != null) sc.close(); 
		}
		data = new String[counter];
		int i = 0;
		try {
			sc = new Scanner(new File("dane/R2006/dane.txt"));
			while (sc.hasNext()) {
					data[i++] = sc.next().trim();
			}
		} catch (IOException e) {
			System.out.println("brak pliku");
		} finally {
			if (sc != null)
				sc.close();
		}
	}

}
