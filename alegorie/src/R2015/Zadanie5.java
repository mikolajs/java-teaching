package R2015;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/* demografia */
public class Zadanie5 {

	public static void main(String[] args) {
		System.out.println();
		
		
	}
	
	
	void load() {
		Scanner sc;
		StringBuilder sb = new StringBuilder();
		try {
			sc = new Scanner(new File("dane/R2014/NAPIS.TXT"));
			while(sc.hasNextLine()) {
				sb.append(sc.nextLine().trim());
				sb.append("\n");
			}
		} catch (IOException e) {
			System.out.println("brak pliku");
		}
	}
}

class Land {
	public int nr;
	public char region;
	public int w2013;
	public int w2014;
	public int m2013;
	public int m2014;
}