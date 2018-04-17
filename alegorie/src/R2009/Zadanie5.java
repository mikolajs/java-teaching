package R2009;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/* Para Słów zadanie 312 */
public class Zadanie5 {
	P[] data;

	public static void main(String[] args) {
		Zadanie5 zad = new Zadanie5();
		System.out.println("Zadanie5 312");
		zad.load();
	}


	private void load() {
		Scanner sc = null;
		int counter = 0;
		try {
			sc = new Scanner(new File("dane/R2006/dane.txt"));
			while (sc.hasNextLine()) {
				sc.nextLine();
				counter++;
			}
		} catch (IOException e) {
			System.out.println("brak pliku");
		} finally {
			if (sc != null)
				sc.close();
		}
		data = new P[counter];
		int i = 0;
		try {
			sc = new Scanner(new File("dane/R2009/dane.txt"));
			P p;
			while (sc.hasNext()) {
				p = new P();
				p.a = sc.next();
				p.b = sc.next();
				data[i++] = p;
			}
		} catch (IOException e) {
			System.out.println("brak pliku");
		} finally {
			if (sc != null)
				sc.close();
		}
	}
}

class P {
	public String a;
	public String b;
}
