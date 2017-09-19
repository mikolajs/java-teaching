import java.util.ArrayList;

public class SuperSieve {
	final int SIZE = 100;
	//if false is prime
	boolean[] sieve;
	ArrayList<Integer> primes;
	int start = 0;
	
	public SuperSieve() {
		sieve = new boolean[10_000_000];
		primes = new ArrayList<Integer>();
		mkSieve();
	}
	
	private void mkSieve() {
		firstSieve();
		for(int i = 1; i < 100; i++) {
			mkLoop();
			start += sieve.length;
		}	
	}
	
	private void mkLoop() {
		int d, w;
		//scratch counted primes
		for(int p : primes) {
			w = start / p;
			d = p * w;
			if(d < start) d += p;
			while(d < start + sieve.length) {
				sieve[d] = true;
				d += p;
			}
		}
		countPrimes();
	}
	//dokończyć!!!!
	private void firstSieve() {
		int p = 0;
		int w = 0;
		for(int i = 2; i < sieve.length; i++) {
			if(!sieve[i])
				while(true) {
					if(w >= sieve.length) break;
				}
			
		}
	}
	
	
	private void countPrimes() {
		for(int i = 0; i < sieve.length; i++) {
			if( start == 0) i = 2;
			if (!sieve[i]) primes.add(i + start);
			else sieve[i] = false;
		}
	}
	
	public int count() {
		return primes.size();
	}
	
	public void print(int start, int end) {
		
	}
}
