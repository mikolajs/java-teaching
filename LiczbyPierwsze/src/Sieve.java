
public class Sieve implements Primes {
    boolean[] sieve;

    public Sieve(int size) {
        sieve = new boolean[size];
        mkSieve();
    }

    private void mkSieve() {
        for(int i = 2; i < sieve.length; i++) {
            if(sieve[i]) continue;
            int k = i;
            while(true) {
                k += i;
                if(k < sieve.length) {
                    sieve[k] = true;
                } else break;
            }
        }
    }

    public int howManyPrimes() { 
        int size = 0;
        for(int i = 2; i < sieve.length; i++) {
            if(!sieve[i]) size++;
        }
        return size;
    }

    public void print(int start, int end) {
        for(int i = start; i < end; i++) {
            if(!sieve[i]) System.out.print(i + " ");
        }
        System.out.println();
    }

    public String toString() {
        return "Sito Erostatenesa jednotablicowe.";
    }
}
