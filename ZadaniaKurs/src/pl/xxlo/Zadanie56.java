package pl.xxlo;

import java.math.BigInteger;

public class Zadanie56 {

	public static void main(String[] args) {
		Zadanie56 z = new Zadanie56();
		int a = 10;
		System.out.println("Podsilnia z liczby " + 10 + " wynosi: " 
				+ z.subfactorial(a));
	}
	
	
	public long subfactorial(int n) {
		long sub = 0L;
		int s = 1; 
		if(n <= 0) return 1;
		while( n >  1) {
			s *= n;
			n--;
		}
		//return s;
		return sub;
	}
	
}
