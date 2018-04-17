package P2009;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/* zadanie 292 */
public class Zadanie5 {
	private boolean[] primeSieve;
	private int[] primes;
	private int[] data;

	public static void main(String[] args) {
		Zadanie5 zad = new Zadanie5();
		
	}
	
	public Zadanie5() {
		sieve();
		//printPrimes();
		loadFile();
		solve();
	}
	
	private void sieve() {
		primeSieve = new boolean[1000];
		for(int i = 2; i < (int) Math.sqrt(1000.0);i++) {
			int j = 2*i;
			while(j < primeSieve.length) {
				primeSieve[j] = true;
				j += i;
			}
		}
		int p = 0;
		for(int i = 2; i < primeSieve.length; i++)
			if(!primeSieve[i]) p++;
		primes = new int[p];
		p = 0;
		for(int i = 2; i < primeSieve.length; i++) {
			if(!primeSieve[i]) {
				primes[p++] = i;
			}
		}
	}
	
	private void printPrimes() {
		for(int i = 0; i < primes.length; i++)
			System.out.println(primes[i] + "\t");
		System.out.println();
	}
	
	private void loadFile() {
		Scanner sc = null;
		data = new int[500];
		int counter = 0;
		try {
			sc = new Scanner(new File("dane/P2009/liczby.txt"));
			while(sc.hasNextInt()) {
				if(counter < data.length) {
				  data[counter++] = sc.nextInt();	
				} else System.out.println("Za dużo linii???");
			}
		} catch (IOException e) {
			System.out.println("brak pliku");
		} finally {if(sc != null) sc.close(); }
	}
	
	private void solve() {
		int j;
		for(int i : data) {
			j = (int) Math.sqrt(i);
			int s = 0;
			while(j < primes[s]) {
				s++;
				if(s >= primes.length) break;
			}
			while(s < primes.length && primes[s]*primes[s] <= i) {
				if(primes[s]*primes[s] == i) {
					System.out.println(i);
					break;
				} else {
					s++;
				}
			}
		}
	}
	
	/////prostszy sposób
	public void solve2() {
		int tmp;
		for(int numb : data) {
			tmp = (int) Math.sqrt(numb);
			if(tmp*tmp == numb) {
				if(isPrime(tmp)) 
					System.out.println(numb);
			}
		}
	}
	

	
	private boolean isPrime(int number) {
		for(int i = 2; i <= Math.sqrt(number); i++) {
			if(number % i == 0) return false;
		}
		return true;
	}

}
