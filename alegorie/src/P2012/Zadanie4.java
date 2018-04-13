package P2012;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/* zadanie 295*/
public class Zadanie4 {

	private int[] data;

	public static void main(String[] args) {

		Zadanie4 zad = new Zadanie4();
		zad.solveA();
		zad.solveB();
		zad.solveC();
	}

	private void solveA() {
		int odd = 0;
		int even = 0;
		System.out.println("A) przyste i nieparzyste");
		for(int n : data) {
			if(n %2 == 0) even++;
			else odd++;
		}
		System.out.println("Parzystych: " + even + " nieparzystych: " + odd);
	}

	private void solveC() {
		System.out.println("C) Liczby tworzące ciąg rosnący: ");
		for(int n : data) {
			if(isGrowning(n))
				System.out.println(n);
		}
	}

	private void solveB() {
		System.out.println("B) Liczba o największej i najmniejszej sumie cyfr");
		int minData = Integer.MAX_VALUE;
		int maxData = Integer.MIN_VALUE;
		int minSum = Integer.MAX_VALUE;
		int maxSum = Integer.MIN_VALUE;
		int tmp;
		for(int n : data) {
			tmp = sumOfCipher(n);
			if(minSum > tmp) {
				minSum = tmp;
				minData = n;
			} else if(maxSum < tmp) {
				maxSum = tmp;
				maxData = n;
			}
		}
		System.out.println("Liczba o najmniejszej sumie cyfr:  " + minData);
		System.out.println("Liczba o największej sumie cyfr:  " + maxData);
	}

	public Zadanie4() {
		loadFile();
	}

	private void loadFile() {
		Scanner sc = null;
		data = new int[1000];
		int counter = 0;
		try {
			sc = new Scanner(new File("dane/P2012/cyfry.txt"));
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
	
	private int sumOfCipher(int number) {
		int sum = 0;
		while(number > 0) {
			sum += number % 10;
			number /= 10;
		}
		return sum;
	}
	private boolean isGrowning(int number) {
		int prev = 10;
		while(number > 0) {
			if(number % 10 >= prev) 
				return false;
			else {
				prev = number % 10;
				number /= 10;
			}
		}
		return true;
	}
}
