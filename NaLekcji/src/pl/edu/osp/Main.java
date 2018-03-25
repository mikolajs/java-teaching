package pl.edu.osp;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

class Main {



    public String koduj(String alamakota) {
        String zakodowany = "";
        int k = 0;
        int j;
        for (int i = 0; i < alamakota.length(); i++) {
            j = (int) alamakota.charAt(i);
            k++;
            if (k > 3)
                k = 1;

            if(j != 32)
                j += k;
            else
                k--;
            zakodowany += (char) j;
           
        }
        return zakodowany;
    }

    public String odkoduj(String zakodowany) {
        String odkodowany = "";
        int k = 0;
        int w;
        for (int q = 0; q < zakodowany.length(); q++) {
            w = (int) zakodowany.charAt(q);
            k++;
            if (k > 3)
                k = 1;
            if(w != 32)
            w -= k;
            else
                k--;
            odkodowany += (char) w;
        }
        return odkodowany;
    }

    public static void main(String[] args) {
        System.out.println("Podaj napis");
        Scanner sc = new Scanner(System.in);
        String alamakota = sc.nextLine().toUpperCase();
        Main k = new Main();
        String zakodowany = k.koduj(alamakota);
        System.out.println(zakodowany);
        String odkodowany = (k.odkoduj(zakodowany));
        System.out.println(odkodowany);
        sc.close();
    }

}
