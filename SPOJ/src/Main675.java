import java.util.Scanner;

/* SkarbFinder 
 *  0 - północ
    1 - południe
    2 - zachód
    3 - wschód
 * */
public class Main675 {
    public static void main(String[] args) {
        System.out.println();
        Scanner sc = new Scanner(System.in);
        int D = sc.nextInt();
        int N;
        int x, y, a, b;
        for (int i = 0; i < D; i++) {
            N = sc.nextInt();
            x = 0;
            y = 0;
            for (int j = 0; j < N; j++) {
                a = sc.nextInt();
                b = sc.nextInt();
                switch (a) {
                case 0:
                    y += b;
                    break;
                case 1:
                    y -= b;
                    break;
                case 2:
                    x -= b;
                    break;
                case 3:
                    x += b;
                    break;
                default:
                    //System.out.println("Nieprawidłowa dana!");
                    break;
                }
            }
            if (y == 0 && x == 0)
                System.out.println("studnia");
            else {
                if (y > 0)
                    System.out.println("0 " + y);
                else if (y < 0)
                    System.out.println("1 " + -y);
                if (x > 0)
                    System.out.println("3 " + x);
                else if (x < 0)
                    System.out.println("2 " + -x);
            }
        }

        sc.close();
    }
}
