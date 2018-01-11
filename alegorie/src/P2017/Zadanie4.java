package P2017;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Numbers {
	int a;
	int b;
	int c;
	public Numbers(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
}

public class Zadanie4 {
	
	public Zadanie4() {
		File file = new File("dane/R2017/binarne.txt");
		Numbers[] nums = new Numbers[1000];
		int i = 0;
		try {
		Scanner sc = new Scanner(file);
		 while(sc.hasNextLine()) {
			nums[i] = new Numbers(sc.nextInt(), sc.nextInt(), sc.nextInt());
			i++;
		 }
		} catch (FileNotFoundException e) {
			System.out.println("Brak pliku \n" + e);
		}
	}
	
	private void solv1() {
		
	}
	
	@Override
	public String toString() {
		return "";
	}
}
