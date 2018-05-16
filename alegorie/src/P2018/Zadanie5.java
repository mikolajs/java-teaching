package P2018;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Zadanie5 {
	int[] data;
	public static void main(String[] args) {
		Zadanie5 z = new Zadanie5();
		z.loadFile();
		z.solve1();
		z.solve2();
		z.solve3();
		
	}
	
	private void solve1() {
		int max = -1;
		for(int n : data) {
			if(n % 2 == 0 && n > max) 
				max = n;
		}
		System.out.println("1) max parzysta " + max);
	}

	private void solve2() {
		System.out.println("2) liczby palindromiczne: ");
		for(int n : data ) {
			if(isPalindrom(String.valueOf(n))) 
				System.out.println(n);
		}
	}
	
	private boolean isPalindrom(String str) {
		int size = str.length()/2;
		int b = 0, end = str.length() -1;
		while(b < size) {
			if(str.charAt(b++) != str.charAt(end--))
				return false;
		}
		return true;
	}

	private void solve3() {
		System.out.println("3) sumy cyfr: ");
		int sum = 0;
		int t;
		for(int n : data) {
			t = ciphreSum(n);
			sum += t;
			if(t > 30)
				System.out.println(n);
		}
		System.out.println("Suma wszystkich cyfr: " + sum);
	}
	
	private int ciphreSum(int n) {
		int s = 0;
		while(n != 0) {
			s += n % 10;
			n /= 10;
		}
		return s;
	}

	private void loadFile() {
        Scanner sc = null;
        data = new int[1000];
        int counter = 0;
        try {
            sc = new Scanner(new File("dane/P2018/liczby.txt"));
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

}
