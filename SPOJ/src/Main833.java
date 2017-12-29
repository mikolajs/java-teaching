import java.util.Scanner;

/* BINOMS - Dwumiany */
public class Main833 {
    public static void main(String[] args) {
        System.out.println("");
        Scanner sc = new Scanner(System.in);
        int D = sc.nextInt();
        int n = 0;
        int k = 0;
        for(int i = 0; i < D; i++) {
            n = sc.nextInt();
            k = sc.nextInt();
            System.out.println( silnia(n)/(silnia(k)*silnia(n-k)));
        }
        int top = 1;
        int bottom = 1;
        if(k > n - k) {
            for(int i = k; i < n; i++) top *= k;        
            System.out.println(top/silnia(n-k));
            
        } else {
            //for(int i = )
        }
        sc.close();
    }
    
    public static int silnia(int n) {
        int r = 1;
        while(n > 1) {
            r *= n;
            n--;
        }
        return n;
    }
}
