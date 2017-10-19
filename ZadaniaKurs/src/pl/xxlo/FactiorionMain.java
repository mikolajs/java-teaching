package pl.xxlo;

import java.util.Random;

class Factorion {
    int number;
    int size;
    long[] factorials;
    
    public Factorion(int size) {
        this.size = size;
        factorials = new long[16];
        factorials[0] = 1;
        factorials[1] = 1;
        for(int i = 2; i < 16; i++)
            factorials[i] = fact((long) i);
    }
    public void set(int n) { number = n;}
    
    public boolean isDecFactorion() {
        return isFactorion(10);
    }
    
    public boolean isOctFactorion() { 
        return isFactorion(8);
    }
    
    public boolean isHexFactorion() {
        return isFactorion(16);
    }
    
    
    private boolean isFactorion(int base) {
        int i = number;
        int c;
        long s = 0;
        while(i > 0) {
           c = i % base;
           i /= base;
           s += factorials[c];
        }
        if(s == (long) number) return true;
        else return false;
    }
    
    private long fact(long n) {
        long f = 1;
        for(long i = 2; i <= n; i++)
            f *= i;
        return f;
    }
    
    public String info() {
        return String.format("Liczba %d %s Oktalnie, %s Decymalnie,"
                + " %s Heksalnie Factorionem", number, textInfo(isOctFactorion()),
                textInfo(isDecFactorion()), textInfo(isHexFactorion()));
    }
    
    public void findAll() {
        for(int i = 1; i < size; i++) {
            number = i;
            for(int j = 2; j < 17; j++) {
                if(isFactorion(j)) 
                    System.out.println(i + " is factorion in base " + j);
            }
        }
    }
    
    private String textInfo(boolean is) {
       if(is) return "JEST";
       else return "NIE JEST";
    }
   
    public void testFactorial() {
        for(int i = 0; i < factorials.length; i++) {
            System.out.println(i + "! = " + factorials[i]);
        }
    }
}

public class FactiorionMain {

    public static void main(String[] args) {
        Factorion f = new Factorion(10_000_000);
       f.findAll();
       System.out.println("END");
    }

}
