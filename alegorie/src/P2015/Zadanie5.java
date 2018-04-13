package P2015;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Zadanie5 {
    
    String[] slowa;
    String[] nowe;

    public static void main(String[] args) {

        System.out.println();
        Zadanie5 zad = new Zadanie5();
        zad.solve1();
        zad.solve2();
        
    }
    
    private void solve1() {
        int[] tab = new int[13];
        for(String s : slowa) {
            if(s.length() < 13) tab[s.length()]++;
        }
        System.out.println("1) Ilość liter w słowach: ");
        for(int i = 1; i < tab.length; i++) {
            System.out.println(i + " ; " + tab[i]);
        }
    }

    private void solve2() {
        int n = 0;
        int m = 0;
        String rs = "";
        for(String sN : nowe) {
            n = 0;
            m = 0;
            rs = reverse(sN);
            for(String sS : slowa) {
                if(sN.equals(sS)) n++;
                if(sS.equals(rs)) m++;
            }
            for(int i = sN.length(); i < 12; i++)
                sN += " ";
            System.out.println(sN +  "\t" + n + "\t" + m);
        }
    }

    public Zadanie5() {
        loadFile();
    }
    
    
    private void loadFile() {
        Scanner sc = null;
        slowa = new String[1000];
        int counter = 0;
        nowe = new String[25];
        try {
            sc = new Scanner(new File("dane/P2015/slowa.txt"));
            while (sc.hasNext()) {
                if (counter < slowa.length) {
                    slowa[counter++] = sc.next();
                } else
                    System.out.println("Za dużo linii???");
            }
            sc.close();
            counter = 0;
            sc = new Scanner(new File("dane/P2015/nowe.txt"));
            while (sc.hasNext()) {
                if (counter < nowe.length) {
                    nowe[counter++] = sc.next();
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
    
    private String reverse(String s) {
        String r  = "";
        for(int i = s.length() -1; i >= 0; i--) {
            r += s.charAt(i);
        }
        return r;
    }

}
