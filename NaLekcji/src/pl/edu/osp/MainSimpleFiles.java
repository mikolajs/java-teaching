package pl.edu.osp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class MainSimpleFiles {

	public static void load() {
		File file = new File("file.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Nie znaleziono pliku");
			System.exit(0);
		}
		int lines = 0;
		while (scanner.hasNextLine()) {
			lines++;
			System.out.println(lines + " " + scanner.nextLine());
		}
		scanner.close();
		try {
			scanner = new Scanner(file);

		} catch (FileNotFoundException e) {
			System.exit(0);
		}
		while (scanner.hasNextLine()) {
			System.out.format("Imię: %s, Nazwisko: %s, wynik: %d\n", scanner.next(), scanner.next(), scanner.nextInt());
		}

		scanner.close();

		String data = "";
		try {
			data = new String(Files.readAllBytes(Paths.get("file.txt")));
		} catch (IOException e) {
			System.exit(0);
		}
		System.out.println(data);
	}

	public static void save() {
		String str = "Tutaj są przygotowane do zapisu dane";
		try {
			Files.write(Paths.get("file2.txt"), str.getBytes());
		} catch (IOException e) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {

		solution1();
		solution2();

	}

	public static void mkFile() {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 100_000; i++) {
			sb.append(Math.abs(rand.nextLong()) % 1000_000_000_000_000L + " ");
		}
		try {
			Files.write(Paths.get("srednia.txt"), sb.toString().getBytes());
		} catch (IOException e) {
			System.exit(0);
		}
	}

	public static void solution1() {
		File file = new File("suma.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Nie znaleziono pliku");
			System.exit(0);
		}
		int suma = 0;
		while (scanner.hasNextInt()) {
			suma += scanner.nextInt();
			// System.out.println(lines + " " + scanner.nextLine());
		}
		scanner.close();

		System.out.format("Suma wynosi: %d\n", suma);

	}
	
	public static void solution2() {
		File file = new File("srednia.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Nie znaleziono pliku");
			System.exit(0);
		}
		int numbers = 0;
		while (scanner.hasNextLong()) {
			numbers++;	
			scanner.next();
		}
		scanner.close();
		System.out.format("Ilość liczb: %d\n", numbers);
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Nie znaleziono pliku");
			System.exit(0);
		}
		long tmp = 0;
		long avg = 0;
		long r = 0;
		final long n = numbers;
		while (scanner.hasNextLong()) {
			tmp =  scanner.nextLong() ;
			avg += tmp / n;
			r += tmp % n;
		}
		avg += r / n;
		r %= n;
		
		System.out.format("Średnia: %d i %d / %d \n", avg, r, n);
	}

}
