package R2014;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Zadanie5 {

	public static void main(String[] args) {
		Zadanie5 zad = new Zadanie5();
		System.out.println();
		//zad.test();
		String[] dane = zad.load();
		System.out.println("Ilość napisów pierwszych: " +
		zad.numberOfPrimes(dane));
		System.out.println("Napisy rosnące: ");
		for(String s : dane) {
			if(zad.isGrowning(s)) 
				System.out.println(s);
		}
		System.out.println("Napisy powtarzające się: ");
		for(String s : zad.theSame(dane)) {
			System.out.println(s);
		}
	}

	public int numberOfPrimes(String[] str) {
		int n = 0;
		for (String s : str) {
			//System.out.println(s +  " : " + asciiToInt(s));
			if (isPrime(asciiToInt(s)))
				n++;
		}
		return n;
	}

	public int asciiToInt(String str) {
		int a = 0;
		for (char l : str.toCharArray()) {
			a += (int) l;
		}
		return a;
	}

	public boolean isPrime(int number) {
		int s = (int) Math.sqrt(number);
		for (int i = 2; i <= s; i++) {
			if (number % i == 0)
				return false;
		}
		return true;
	}

	public boolean isGrowning(String str) {
		for(int i = 1; i < str.length(); i++) {
			if((int) str.charAt(i-1) > str.charAt(i)) 
				return false;
		}
		return true;
	}

	public String[] theSame(String[] str) {
		StringBuilder sb = new StringBuilder();
		List<String> list = Arrays.asList(str);
		list.stream().sorted().
				collect(Collectors.toList()).toArray(str);
		for(int i = 1; i < str.length; i++) {
			if(str[i-1].equals(str[i])) sb.append(str[i] + "\n");
		}
		return sb.toString().split("\n");
	}

	public void test() {
		assert asciiToInt("ABB") == 197;
		assert asciiToInt("AGZAB") == 357;
		assert isPrime(130) == false;
		assert isPrime(1721) == true;
		assert isPrime(3542) == false;
		assert numberOfPrimes(new String[] { "ABB", "AGZAB" }) == 1;

	}

	public void save(String str) {
		try {
			Files.write(Paths.get("plik.txt"), str.getBytes());
		} catch (IOException e) {
			System.out.println("Błąd zapisu");
		}
	}
	
	public String[] load() {
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
		return sb.toString().split("\n");
	}
}
