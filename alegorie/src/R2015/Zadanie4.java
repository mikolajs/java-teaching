package R2015;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


/* zadanie 46 */
public class Zadanie4 {
	String[] dane;
	
	public static void main(String[] args) {
		Zadanie4 z = new Zadanie4();
		z.load();
		z.solve1();
		z.solve2();
		z.solve3();
	}
	
	private void solve1() {
		int zeros = 0;
		int n = 0;
		for(String s : dane) {
			n = 0;
			for(char c : s.toCharArray()) {
				if(c == '0') n++;
			}
			if(n > s.length()/2) zeros++;
		}
		System.out.println("1) Więcej zer niż jedynek " + zeros);
	}

	private void solve2() {
		int even = 0;
		int oct = 0;
		for(String s : dane) {
			if(s.charAt(s.length()-1) == '0') {
				even++;
				if(s.charAt(s.length() - 2) == '0' && s.charAt(s.length()-3) == '0')
						oct++;
			}
		}
		System.out.println("2) Podzielne przez 2 " + even + " podzielne przez 8 "+ oct);
	}

	private void solve3() {
		int maxLine = -1;
		int minLine = -1;
		int maxLong = -1;
		int minLong = 1000;
		String max = "";
		String min = "";
		for(int i = 0; i < dane.length; i++) {
			if(dane[i].length() > maxLong) {
				max = dane[i];
				maxLine = i;
				maxLong = dane[i].length();
			} else if(dane[i].length() == maxLong) {
if(max.compareTo(dane[i]) < 0) {
					max = dane[i];
					maxLine = i;
					maxLong = dane[i].length();
				}
			}
			if(dane[i].length() < minLong) {
				min = dane[i];
				minLine = i;
				minLong = dane[i].length();
			} else if(dane[i].length() == minLong) {
				if(min.compareTo(dane[i]) > 0) {
					min = dane[i];
					minLine = i;
					minLong = dane[i].length();
				}
			}
		}
		//numer liczymy od jeden więc zwiększam minLine i maxLine
		System.out.println("3) Najmniejsza w wierszu: " + ++minLine + 
				" największa w: " + ++maxLine);
	}

	private void load() {
		dane = new String[1000];
		File file = new File("dane/R2015/liczby.txt");
		Scanner sc = null;
		int licznik = 0;
		try {
			sc = new Scanner(file);
			while(sc.hasNext()) {
				dane[licznik++] = sc.next().trim();
			}
		} catch(IOException e) {
			System.out.println("Błąd pliku");
		} finally {
			if(sc != null) sc.close();
		}
	}
	
	
}
