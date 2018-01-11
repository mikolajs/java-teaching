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
            a = sc.next();
            b = sc.next();
            BigInteger bint1 = new BigInteger(a);
            BigInteger bint2 = new BigInteger(b);
            System.out.println(bint1.multiply(bint2).toString());
            System.out.println(solution(a, b));
            assert bint1.multiply(bint2).toString() == solution(a, b);
        }
        
    }
    
    public static String solution(String a, String b){
        char[] A = a.toCharArray();
        char[] B = b.toCharArray();
        int[] C = new int[A.length];
        for(int i = 0; i < A.length; i++) {
            C[i] = (int) A[i] - 48;
        }
        for(int i = 0; i < B.length; i++) {
            for(int j = 0; j < C.length; j++) {
                C[j] *= (int) B[i];
            }
        }
        
        return "";
    }
}
