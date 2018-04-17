package pl.edu.osp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int[] wagi = { 1, 3, 7, 9, 1, 3, 7, 9, 1, 3 };
        long[] pesel = new long[150];
        int a = 0;
        int b = 0;
        int suma = 0;
        int kontrolna = 0;
        File file = new File("/home/ms/Dropbox/school/LO/matury informatyka/2010/Dane_PR/pesel.txt");
        Scanner sc = new Scanner(file);
        for (int i = 0; i < 150; i++) {
            pesel[i] = sc.nextLong();
        }
        for (long p : pesel) {
            suma = 0;
            String str = String.valueOf(p);
            if (str.charAt(2) == '1' && str.charAt(3) == '2')
                a++;
            if ((int) (char) (int) str.charAt(9) % 2 == 0)
                b++;
            for (int k = 0; k < 10; k++) {
                suma += (int) (char) (int) str.charAt(k) * wagi[k];
            }
            if (suma % 10 != 0)
                kontrolna = 10 - (suma % 10);
            if (str.charAt(10) != (char) kontrolna)
                System.out.println(p);
        }
        System.out.println(a);
        System.out.println(b);

    }

}
        
