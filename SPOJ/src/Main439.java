import java.math.BigInteger;
import java.util.Scanner;

/* TMUL - Not So Fast Multiplication */
public class Main439 {
    public static void main(String[] args) {
        System.out.println("");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String a, b;
        for(int i = 0; i < n; i++) {
        	a = sc.next().trim();
        	b = sc.next().trim();
        	System.out.println("bigInt = " + testBigInt(a, b));
        	System.out.println("solution = " + solution(a, b));
        	assert testBigInt(a, b) == solution(a, b);
        }
        sc.close();
    }
    
    
    public static String testBigInt(String a, String b) {
    	BigInteger bint1 = new BigInteger(a);
        return bint1.multiply(new BigInteger(b)).toString();
    }
    
    public static String solution(String a, String b) {
    	char[] A = a.toCharArray();
    	char[] B = b.toCharArray();
    	//System.out.println(A);
    	int[] C = new int[A.length+B.length-1];
    	//mnożenie
    	for(int i = B.length - 1; i >= 0; i--)
    		for(int j = A.length - 1; j >= 0; j--) {
    		  System.out.println(((int) B[i] - 48) + " * " + ((int) A[j] - 48) + " = " + (((int) B[i]) -48) * (((int) A[j] - 48)));
    		   C[i+j] += ((int) B[i] -48) * ((int) A[j] - 48);	
    		}
    	
    	for(int i = 0; i < C.length; i++) {
    		System.out.print(C[i]);
    	}
    	System.out.println();
    	//redukcja
    	
    	
    	//wydruk
    	StringBuilder sb = new StringBuilder();
    	for(int d : C) sb.append(d);
    	
    	
    	return sb.toString();
    }
}