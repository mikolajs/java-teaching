package pl.xxlo;

import java.util.ArrayList;

interface Primes {
    int howManyPrimes();
    void print(int start, int end);
}

class PrimitivePrimes implements Primes {
    private int[] primes;
    private int index = -1;
    final int RANGE;
    public PrimitivePrimes(int range) {
        RANGE = range;
        primes = new int[RANGE];
        cout();
    }
    
   private void cout() {
       boolean prime;
       for(int i = 2; i < RANGE; i++) {
           prime = true;
           for(int j = 2; j <= (int) Math.sqrt(i); j++) {
               if(i % j == 0) {
                   prime = false;
                   break;
               }
           }
           if(prime) {
               index++;
               primes[index] = i;
           }
       }
   }
  
   public int howManyPrimes() {
       return index + 1;
   }
   
   public void print(int start, int end){
       for(int i = 0; i <= index; i++ ) {
           if(primes[i] >= end) break;
           if(primes[i] > start) System.out.print(primes[i] +  " ");
       }
       System.out.println(" koniec");
   }
   public String toString() {
       return "Prymitywne wyliczanie liczb pierwszych.";
   }
}


class Sieve implements Primes {
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


class SuperSieve implements Primes {
    //if false is prime
    boolean[] sieve;
    ArrayList<Integer> primes;
    int start = 0;
    
    public SuperSieve(int size) {
        sieve = new boolean[size/10];
        primes = new ArrayList<Integer>();
        mkSieve();
    }
    
    private void mkSieve() {
        firstSieve();
        for(int i = 1; i < 10; i++) {
            start += sieve.length;
            mkLoop();
        }   
    }
    
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
    
    public int howManyPrimes() {
        return primes.size();
    }
    
    public void print(int start, int end) {
        for(int prime : primes) {
            if(prime >= end) break;
            if(prime >= start) System.out.print(prime + "\t");
        }
        System.out.println();
    }
    
    public String toString() {
        return "Super sito Erostatenesa";
    }
}


public class MainPrimes {

    public static void main(String[] args) {
        long deltaTime = 0;
        final int size = 10_000_000;
        final int start = 200;
        final int stop = 250;
        Primes p;

        deltaTime = System.currentTimeMillis();
        p = new PrimitivePrimes(size);
        deltaTime = System.currentTimeMillis() - deltaTime;
        System.out.println(p.toString() + " Time " + deltaTime);
        System.out.println("liczb pierwszych: " + p.howManyPrimes());
        p.print(start, stop);

        deltaTime = System.currentTimeMillis();
        p = new Sieve(size);
        deltaTime = System.currentTimeMillis() - deltaTime;
        System.out.println(p.toString() + " Time " + deltaTime);
        System.out.println("liczb pierwszych: " + p.howManyPrimes());
        p.print(start, stop);

        deltaTime = System.currentTimeMillis();
        p = new SuperSieve(size);
        deltaTime = System.currentTimeMillis() - deltaTime;
        System.out.println(p.toString() + " Time " + deltaTime);
        System.out.println("liczb pierwszych: " + p.howManyPrimes());
        p.print(start, stop);

    }

}

