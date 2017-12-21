import java.util.Scanner;

public class Main499 {

    public static void main(String[] args) {
        System.out.println("Podaj D i a oraz b");
        Scanner s = new Scanner(System.in);
        int D = s.nextInt();
        int a, b, c, x;
        for(int i = 0; i < D; i++) {
            a = s.nextInt() % 10;
            b = s.nextInt();
            c = a;
            x = 1;
            while(b > 1) {
               if(b % 2 == 1) {
                   x *= c;
                   x %= 10;
               }             
               b /= 2;
               c *= c; 
               c %= 10;   
            }
            System.out.println((c * x) % 10);
        }
        
        s.close();
    }
    
    // 3 5 => 3, 7 5 => 7, 2 21 => 2

}
