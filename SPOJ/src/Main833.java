import java.util.Scanner;

/* BINOMS - Dwumiany */
public class Main833 {
    public static void main(String[] args) {
        System.out.println("");
        Scanner sc = new Scanner(System.in);
        int D = sc.nextInt();
        int n, k;
        for(int i = 0; i < D; i++) {
            n = sc.nextInt();
            k = sc.nextInt();
            System.out.println( silnia(n)/(silnia(k)*silnia(n-k)));
        }
        sc.close();
    }
    
    public static int silnia(int n) {
        int r = 1;
        while(n > 1) {
            r *= n;
            n--;
        }
        return r;
    }
}
