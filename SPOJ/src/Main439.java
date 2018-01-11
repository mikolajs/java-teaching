import java.math.BigInteger;
import java.util.Scanner;

/* TMUL - Not So Fast Multiplication */
/* 
 2 
 234435455645645654634534545 56456456456456456456456 
 443443293485984953049583945849839894853948594854 49853948402495824852934852098452398504854
 */
public class Main439 {
    public static void main(String[] args) {
        System.out.println();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String a, b;
        for(int i = 0; i < n; i++) {
        	a = sc.next().trim();
        	b = sc.next().trim();
//        	System.out.println("bigInt = " + testBigInt(a, b));
//        	System.out.println("solution = " + solution(a, b));
//        	assert testBigInt(a, b).equals(solution(a, b));
        	System.out.println(solution(a, b));
        }
        sc.close();
    }
    
    
//    public static String testBigInt(String a, String b) {
//    	BigInteger bint1 = new BigInteger(a);
//        return bint1.multiply(new BigInteger(b)).toString();
//    }
    
    public static String solution(String a, String b) {
    	char[] A = a.toCharArray();
    	char[] B = b.toCharArray();
    	//System.out.println(A);
    	int[] C = new int[A.length+B.length];
    	//mnożenie
    	for(int i = B.length - 1; i >= 0; i--)
    		for(int j = A.length - 1; j >= 0; j--) {
    		   C[i+j+1] += ((int) B[i] -48) * ((int) A[j] - 48);	
    		}

    	System.out.println();
    	//redukcja
    	int num = 0;
    	for(int i = C.length - 1; i >= 0; i--) {
    		num = C[i];
    		C[i] = num % 10;
    		num /= 10;
    		if(num > 0) C[i - 1] += num;
    	}
    	
    	//wydruk
    	StringBuilder sb = new StringBuilder();
    	int i = 0;
    	while(C[i] == 0) i++;
    	while(i < C.length) {
    		sb.append(C[i]);
    		i++;
    	}    	
    	
    	return sb.toString();
    }
}
