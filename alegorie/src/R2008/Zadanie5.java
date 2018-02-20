package R2008;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import R2012.Zadanie4;

public class Zadanie5 {

	public static void main(String[] args) {

		Zadanie5 zad = new Zadanie5();
		System.out.println("Zadanie 5 R2008");
		zad.test();
		System.out.println("A. ");
		String resA = zad.zadA1();
		//System.out.println(resA);
		System.out.println("Największy i najmniejszy:\n" + zad.maxMin(resA));
		// zad.save("dane/R2008/hasla_a.txt",zad.zadA());
		System.out.println("B. ");
		//System.out.println(zad.zadB1());
		String resB = zad.zadB1();
		System.out.println("Długość 12:\n"+zad.longOf12(resB));
		System.out.println("MAX I MIN:\n" + zad.maxMin(zad.zadB1()));
		System.out.println("SUMA znakóœ: " + zad.sumLength(resB) );
		// zad.save("dane/R2008/wynikB.txt",zad.zadB());

	}

	public String zadA1() {
		String[] words = loadData("dane/R2008/slowa.txt");
		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			sb.append(mkKey1(word));
			sb.append("\n");
		}
		return sb.toString();
	}

	public String maxMin(String keys) {
		String[] keysArr = keys.split("\n");
		if(keysArr.length == 0) return "BŁĄÐ!!!!!!!!!!!";
		int max = keysArr[0].length(), min = keysArr[0].length();
		String maxKey = keysArr[0];
		String minKey = keysArr[0];
		for (String key : keysArr) {
			if (key.length() > max) {
				max = key.length();
				maxKey = key;
			} else if (key.length() < min) {
				min = key.length();
				minKey = key;
			}
		}
		return maxKey + " " + max + "\n" + minKey + " " + min + "\n";
	}

//	1. Podaj wszystkie hasła o długości 12.
//	2. Podaj najdłuższe i najkrótsze hasło.
//	3. Podaj sumę długości wszystkich haseł.
	
	
//	
	public String zadB1() {
		String[] words = loadData("dane/R2008/slowa.txt");
		StringBuilder sb = new StringBuilder();
		for(String word : words) {
			sb.append(mkKey2(word));
			sb.append("\n");
		}
		for (int i = 0; i < words.length; i++) {
		}
		return sb.toString();
	}

	private String mkKey1(String word) {
		String key = "";
		for (int i = word.length() - 1; i >= 0; i--) {
			key += word.charAt(i);
		}
		return key;
	}

	private String mkKey2(String word) {
		String palindrom = "";
		int n = word.length();
		while(n > 1) {
			if(isPalindrom(word.substring(0,n))) break;
			else n--;
		}
		palindrom = word.substring(0, n);
		if(palindrom.length() == word.length()) return word;
		else {
			StringBuilder extra = new StringBuilder(
					word.substring(palindrom.length()));
			extra.reverse();
			extra.append(word);
			return extra.toString();
		}
	}

	private boolean isPalindrom(String key) {
		boolean is = true;
		int i = 0;
		int j = key.length() -1;
		while(i < j) {
			if(key.charAt(i) != key.charAt(j)) 
				return false;
			i++; j--;
		}
		return is;
	}
	
	private int sumLength(String data) {
		int sum = 0;
		String[] arr  = data.split("\n");
		for(String str : arr) {
			sum += str.length();
		}
		return sum;
	}
	
	private String longOf12(String data) {
		StringBuilder sb = new StringBuilder();
		String[] arr = data.split("\n");
		for(String str : arr) {
			if(str.length() == 12) {
				sb.append(str);
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	

	private String[] loadData(String path) {
		File file = new File(path);
		Scanner sc = null;
		StringBuilder sb = new StringBuilder();
		try {
			sc = new Scanner(file);
			while (sc.hasNext())
				sb.append(sc.next() + " ");

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (sc != null)
				sc.close();
		}
		return sb.toString().split(" ");
	}

	public void save(String name, String content) {
		try {
			Files.write(Paths.get(name), content.getBytes());
		} catch (IOException e) {
			System.out.println("Path not found, DON'T SAVED");
		}
	}

	private void test() {
		System.out.println( mkKey2("KAJAKARSTWO"));
		
		// System.out.println( code("MARTA", "TOR"));
		assert ("KAJAK".equals(mkKey1("KAJAK")));
		assert ("EGZAMIN".equals(mkKey1("NIMAZGE")));
		assert ("MATURA".equals(mkKey1("ARUTAM")));
		assert ("KOMINIARZ".equals(mkKey1("ZRAINIMOK")));
		
		assert (isPalindrom("K"));
		assert (isPalindrom("KAK"));
		assert (isPalindrom("KAJAK"));
		assert (isPalindrom("KAAK"));
		assert (isPalindrom("KA") == false);
		assert (isPalindrom("KAA") == false);

		assert ("KAJAK".equals(mkKey2("KAJAK")));
		assert ("OWTSRAKAJAKARSTWO".equals(mkKey2("KAJAKARSTWO")));
		assert ("AMAMA".equals(mkKey2("MAMA")));
		assert ("SUTKAKTUS".equals(mkKey2("KAKTUS")));
		assert ("ANNAWANNA".equals(mkKey2("WANNA")));

	}
}
