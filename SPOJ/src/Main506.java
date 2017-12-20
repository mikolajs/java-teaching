import java.util.Scanner;

/* FLAMASTE */
public class Main506 {
    public static void main(String[] args) {
        System.out.println("");
        Scanner s = new Scanner(System.in);
        int c = s.nextInt();
        int  x;
        char last = ' ';
        String a = "";
        for(int i = 0; i < c; i++) {
            a = s.next();
            last = a.charAt(0);
            x = 1;
            for(int j = 1; j < a.length();j++) {
                if(last == a.charAt(j)) {
                    x++;
                } else {
                    if(x > 2) {
                        System.out.print(last);
                        System.out.print(x);

                    } else {
                        for(int k = 0; k < x; k++) System.out.print(last);
                    }
                    last = a.charAt(j);
                    x = 1;
                }
                
            }
            if(x > 2) {
                System.out.print(last);
                System.out.print(x);

            } else {
                for(int k = 0; k < x; k++) System.out.print(last);
            }
            System.out.println();
      }
        
        s.close();
    }
}
