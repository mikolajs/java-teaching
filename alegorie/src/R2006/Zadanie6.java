package R2006;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/* Słowa zadanie 316 */
public class Zadanie6 {
	String[] data;
	public static void main(String[] args) {
		Zadanie6 zad = new Zadanie6();
		System.out.println("Zadanie6 (316)");
		zad.load();
		zad.solveA();
		zad.solveB();
	}
	
	private void solveA() {
		Arrays.sort(data, Comparator.comparing(String::toString));
		String last = "";
		int n = 1;
		int many = 0;
		int maxMany = 0;
		int i = 0;
		String maxString = "";
		for(String s : data) {
			i++;
			if(last.equals(s)) n++;
			else {
				if(n > 1) {
					many++;
					if(maxMany < n) {
						maxMany = n;
						maxString = last;
					}
				}
				n = 1;
				last = s;
			}
			if(i == data.length && n > 1) {
				many++;
				if(maxMany < n) {
					maxMany = n;
					maxString = s;
				}
			}
		}
		
		System.out.println("Ilość wielokrotnych: " + many);
		System.out.println("Słowo o największej liczbie wystąpień: " + maxString);
		System.out.println("Ilość wystąpień: " + maxMany);
		
	}

	private void solveB() {
		char c;
		int n = 0;
		for(String s : data) {
			c = s.charAt(s.length()-1);
			//odwrotnie A to 10 ale kod ascii to 65
			if((int)c % 2 == 1) n++;
		}
		System.out.println("Szesnastkowo, parzystych: " + n);
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
