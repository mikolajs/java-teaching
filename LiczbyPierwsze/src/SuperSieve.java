import java.util.ArrayList;

public class SuperSieve {
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
		for(int i = 0; i < 100; i++) {
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
		//new primes in array;
		
	}
	
	public int count() {
		return primes.size();
	}
	
	public void print(int start, int end) {
		
	}
}
