package pl.edu.osp;

import java.util.ArrayList;

public class SuperSieve {
	final int SIZE = 1_000_000;
	//if false is prime
	boolean[] sieve;
	ArrayList<Integer> primes;
	int start = 0;
	
	public SuperSieve() {
		sieve = new boolean[SIZE];
		primes = new ArrayList<Integer>();
		mkSieve();
	}
	
	private void mkSieve() {
		firstSieve();
		for(int i = 1; i < 100; i++) {
			start += sieve.length;
			mkLoop();
			
		}	
	}
	//poprawić
	private void mkLoop() {
		int d;
		//scratch counted primes
		for(int p : primes) {
			d = (start / p ) * p;
			if(d < start) d += p;
			while(d - start < sieve.length) {
				sieve[d - start] = true;
				d += p;
			}
		}
		notePrimes();
	}

	private void firstSieve() {
		int p = 0;
		for(int i = 2; i < sieve.length; i++) {
			p = i;
			if(!sieve[i])
				while(true) {
					p += i;
					if(p >= sieve.length) break;
					sieve[p] = true;
				}
			
		}
		notePrimes();
	}
	
	
	private void notePrimes() {
		int i = 0;
		if( start == 0) i = 2;
		for(; i < sieve.length; i++) {	
			if (!sieve[i]) primes.add(i + start);
			else sieve[i] = false;
		}
	}
	
	public int count() {
		return primes.size();
	}
	
	public void print(int start, int end) {
		for(int prime : primes) {
			if(prime >= end) break;
			if(prime >= start) System.out.print(prime + "\t");
		}
		System.out.println();
	}
}
