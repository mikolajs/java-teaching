package pl.edu.osp;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Zadanie292 {
	
	private int[] tab;

	public static void main(String[] args) {

		Zadanie292 zad = new Zadanie292();
		
	}
	
	
	public Zadanie292() {
		System.out.println();
		load();
		test();
		solve();
	}
	
	public void solve() {
		int tmp;
		for(int numb : tab) {
			tmp = (int) Math.sqrt(numb);
			if(tmp*tmp == numb) {
				if(isPrime(tmp)) 
					System.out.println(numb);
			}
		}
	}
	
	private void load() {
		Scanner sc = null;
		File f = new File("liczby.txt");
		tab = new int[500];
		//you have been hacked
		try {
			sc = new Scanner(f);
			for(int i = 0; i < tab.length;i++) {
				tab[i] = sc.nextInt();
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			if(sc != null) sc.close();
		}
	}
	
	private boolean isPrime(int liczba) {
		for(int i = 2; i <= Math.sqrt(liczba); i++) {
			if(liczba % i == 0) return false;
		}
		return true;
	}

	private void test() {
		assert isPrime(7) == true;
		assert isPrime(101) == true;
		assert isPrime(169) == false;
		assert isPrime(99) == false;
	}
}
