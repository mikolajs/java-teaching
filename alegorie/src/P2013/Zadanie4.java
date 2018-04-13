package P2013;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/* Napisy – zadanie na sprawdzian */
public class Zadanie4 {
    
    String[] data;

    public static void main(String[] args) {
        Zadanie4 zad = new Zadanie4();
        zad.solveA();
        zad.solveB();
        zad.solveC();
        zad.solveD();
    }
    
    private void solveA() {
       int n = 0;
       for(String s : data) {
           if(s.length() % 2 == 0) n++;
       }
       System.out.println("Parzystych napisów jest " + n);
    }

    private void solveB() {
        int b = 0;
        int n = 0;
        for(String s : data) {
            b = 0;
            for(char c : s.toCharArray()) {
                if(c == '0') b--;
                else b++;
            }
            if(b == 0) n++;
        }
        System.out.println("Napisów o tej samej liczbie zer i jedynek jest " + n); 
    }

    private void solveC() {
        int only0 = 0;
        int only1 = 0;
        char lead;
        int b = 0;
        for(String s : data) {
            lead = s.charAt(0);
            b = s.length();
            for(char c : s.toCharArray()) {
                if(c == s.charAt(0)) b--;
            }
            if(b == 0) {
                if(s.charAt(0) == '0') only0++;
                else only1++;
            };
        }
        System.out.println("Napisów z samymi zerami: " + only0 +
                " i samymi zerami: " + only1); 
    }

    private void solveD() {
        int b = 0;
        int[] tab = new int[17];
        for(String s : data) {
          if(s.length() < 17) tab[s.length()]++; 
        }
        System.out.println("Długość napisów: ");
        for(int i = 2; i < tab.length; i++) {
            System.out.println(i + " : " + tab[i]);
        }
        
    }

    public Zadanie4() {
        loadFile();
    }
    
    
    private void loadFile() {
        Scanner sc = null;
        data = new String[1000];
        int counter = 0;
        try {
            sc = new Scanner(new File("dane/P2013/napisy.txt"));
            while (sc.hasNext()) {
                if (counter < data.length) {
                    data[counter++] = sc.next();
                } else
                    System.out.println("Za dużo linii???");
            }
        } catch (IOException e) {
            System.out.println("brak pliku");
        } finally {
            if (sc != null)
                sc.close();
        }
    }

}
