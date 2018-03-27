package P2016;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Zadanie6 {
		private int[] data;
		public static void main(String[] args) {

			Zadanie6 zad = new Zadanie6();
			zad.solveA();
			zad.solveB();
			zad.solveC();
		}
		
		public Zadanie6() {
			loadFile();
		}
		private void solveA() {
System.out.println("A) Liczby pierwsze w pliku"); 
			int n = 0;
			for(int number : data) {
				if(isPrime(number)) n++;
			}
			System.out.println("Liczb pierwszych: " + n);
		}
		private void solveB() {
			System.out.println("B) Max i min piersze");
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			for(int number : data) {
				if(isPrime(number)) {
					if(number > max) max = number;
					else if(number < min) min = number;
				}
			}
			System.out.println("Max: " + max + "  Min: " + min);
			
		}
		private void solveC() {
			System.out.println("C) Sąsiadujące i pierwsze różniące się o 2"); 
			for(int i = 1; i < data.length; i++) {
				if(isPrime(data[i-1])&&isPrime(data[i])) {
					if(Math.abs(data[i-1]-data[i]) == 2) {
						System.out.println(data[i -1] + " " + data[i]);
					}
				}
			}
		}
		private void loadFile() {
			Scanner sc = null;
			data = new int[2000];
			int counter = 0;
			try {
				sc = new Scanner(new File("dane/P2016/dane_6.txt"));
				while (sc.hasNextInt()) {
					if (counter < data.length) {
						data[counter++] = sc.nextInt();
					} else
						System.out.println("Za dużo linii???");
				}
			} catch (IOException e) {
				System.out.println("brak pliku");
			} finally {
				if (sc != null)
					sc.close();
			}
		}
		
		private boolean isPrime(int number) {
			for(int i = 2; i <= Math.sqrt(number); i++) {
				if(number % i == 0) return false;
			}
			return true;
		}

}
