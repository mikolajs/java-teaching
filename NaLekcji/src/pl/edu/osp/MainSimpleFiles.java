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
		solutionExtra1();
		solutionExtra2();
		divideBy11();
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
		Scanner scanner = loadFile("srednia.txt");
		if(scanner == null) {
			System.out.println("Nie mogę wczytać pliku!");
			return;
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
		Scanner scanner = loadFile("srednia.txt");
		if(scanner == null) {
			System.out.println("Nie mogę wczytać pliku!");
			return;
		}
		int numbers = 0;
		while (scanner.hasNextLong()) {
			numbers++;	
			scanner.next();
		}
		scanner.close();
		System.out.format("Ilość liczb: %d\n", numbers);
		scanner  = loadFile("srednia.txt");
		if(scanner == null) {
			System.out.println("Nie mogę wczytać pliku!");
			return;
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
	
	public static void solutionExtra1() {
		Scanner s = loadFile("srednia.txt");
		if(s == null) {
			System.out.println("Nie mogę wczytać pliku!");
			return;
		}
        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        long tmp = 0;
        while(s.hasNextLong()) {
            tmp = s.nextLong();  
            if(tmp < min) min = tmp;
            else if(tmp > max) max = tmp;
        }
        System.out.println("Maksimum: " + max + ", minimum "  + min);
        
        s.close();
	}
	
	public static void solutionExtra2() {
	    int numbers[] = new int[10];
	    Scanner s = loadFile("suma.txt");
		if(s == null) {
			System.out.println("Nie mogę wczytać pliku!");
			return;
		}
 
        while(s.hasNextInt()) { 
            numbers[s.nextInt() % 10]++;
        }
        int tmp = 0;
        for(int i = 0; i < numbers.length; i++) {
            System.out.print(i + ": ");
            tmp = numbers[i] / 500;
            if(numbers[i] % 500 != 0) tmp++;
            for(int j = 0; j < tmp; j++)
                System.out.print("#");
            System.out.println( " " + numbers[i]);
        }
        
        s.close();
	}
	public static void divideBy11() {
		Scanner sc = loadFile("srednia.txt");
		if(sc == null) {
			System.out.println("Nie mogę wczytać pliku!");
			return;
		}
		int n = 0;
		while(sc.hasNextLong()) {
			if( sc.nextLong() % 11L == 0) n++;
		}
		System.out.println("Ilość podzielnych przez 11 to: " + n);
	}
	
	public static void numbersFormRange() {
		Scanner sc = loadFile("srednia.txt");
		if(sc == null) {
			System.out.println("Nie mogę wczytać pliku!");
			return;
		}
		int n = 0;
		long tmp;
		while(sc.hasNextLong()) {
			tmp = sc.nextLong();
			if(tmp >= 100_000_000L && tmp <= 1000_000_000) n++;
		}
		System.out.println("Ilość liczb z zakresu 100 do 1000 milionów: " + n);
	}
	
	public static Scanner loadFile(String name) {
		File f = new File(name);
        if( !f.exists() && !f.isFile()) {
            System.out.println("Nie mogę znaleźć pliku");
            return null; 
        }
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch(FileNotFoundException e) {
            System.out.println("Nie mogę otworzyć pliku");
            return null;
        }
        return s;
	}

}
