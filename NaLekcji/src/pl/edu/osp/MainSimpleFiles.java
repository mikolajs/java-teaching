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
		} catch(FileNotFoundException e) {
			System.out.println("Nie znaleziono pliku");
			System.exit(0);
		}
		int lines = 0;
		while(scanner.hasNextLine()) {
			lines++;
			System.out.println(lines + " " + scanner.nextLine());
		}
		scanner.close();
		try {
			scanner = new Scanner(file);

		} catch (FileNotFoundException e) {
			System.exit(0);
		}
		while(scanner.hasNextLine()) {
			System.out.format("Imię: %s, Nazwisko: %s, wynik: %d\n", 
					scanner.next(), scanner.next(), scanner.nextInt());
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
		
		
	}
	
	public static void mkFile() {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 100_000; i++) {		
			sb.append(Math.abs(rand.nextLong()) % 1000_000_000_000_000L + " ");
		}
		try {
			Files.write(Paths.get("srednia.txt"), sb.toString().getBytes());
		} catch (IOException e) {
			System.exit(0);
		}
	}

}
