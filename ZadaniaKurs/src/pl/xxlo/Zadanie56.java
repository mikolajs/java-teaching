package pl.xxlo;

import java.math.BigInteger;

public class Zadanie56 {

	public static void main(String[] args) {
		Zadanie56 z = new Zadanie56();

		for(int i = 0; i < 10; i++)
			System.out.println("Podsilnia z liczby " + i + " wynosi: " 
				+ z.subfactorial(i));
		int i = 21;
		System.out.println("Podsilnia z liczby " + i + " wynosi: " 
				+ z.subfactorial(i));
	}
	
	
	public BigInteger subfactorial(int n) {

		BigInteger f = BigInteger.valueOf(1);
		//long s = n % 2 == 0 ? 1 : -1; 
		BigInteger sub = n % 2 == 0 ? BigInteger.valueOf(1L) : BigInteger.valueOf(-1L); 
		if(n <= 0) return BigInteger.valueOf(1);
		else if(n == 1) return BigInteger.ZERO;
		for(int i = n; i > 2; i--) {
			f = f.multiply(BigInteger.valueOf(i));
			if( i % 2 == 0) sub = sub.subtract(f);
			else sub = sub.add(f);
		}
		
		return sub;
	}
	
}
