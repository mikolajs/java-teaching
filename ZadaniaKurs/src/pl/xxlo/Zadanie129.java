package pl.xxlo;

import java.util.Scanner;

class Gra {
    char plansza[][];
    boolean wToku = true;
    Scanner scanner = new Scanner(System.in);
    int krok = 0;
    
    public Gra() {
        plansza = new char[3][3];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++) {
                plansza[i][j] = '.';
            }
    }
    
    public void run() {
        char znak;
        while(wToku) {
            wyswietlPlansze();
            if(krok % 2 == 0) znak = 'O';
            else znak = '#'; 
            pobierzWspolrzedne(znak);
            if(koniecGry()) {
                wyswietlPlansze();
                wToku = false;
            }
            if(krok == 9) wToku = false;
            krok++;
        }        
    }
    
    private void wyswietlPlansze() {
        System.out.println();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.print(plansza[i][j]);
            }
            System.out.println("");
        }
        System.out.println();
    }
    
    
    private void pobierzWspolrzedne(char znak) {
        int wiersz;
        int kolumna;
        boolean error = false;
        do {
            System.out.print(String.valueOf(znak)+ ">  ");
            wiersz = scanner.nextInt();
            kolumna = scanner.nextInt();
            if(wiersz > 0 && wiersz < 4 && kolumna > 0 && kolumna < 4 &&
                    plansza[wiersz-1][kolumna-1] == '.') {
                plansza[wiersz-1][kolumna-1] = znak;
                error = false;
            } else {
                System.out.println("Nieprawidłowe pole");
                wyswietlPlansze();
                error = true;
            }
        } while (error);
         
    }
    
    private boolean koniecGry() {
        if(plansza[0][0] != '.' 
                && plansza[0][0] == plansza[0][1] 
                && plansza[0][1] == plansza[0][2]) {
            System.out.println("Wygrał: " + plansza[0][0]);
            return true;
        } else  if(plansza[1][0] != '.' 
                && plansza[1][0] == plansza[1][1] 
                && plansza[1][1] == plansza[1][2]) {
            System.out.println("Wygrał: " + plansza[1][0]);
            return true;
        }  else if(plansza[2][0] != '.' 
                && plansza[2][0] == plansza[2][1] 
                && plansza[2][1] == plansza[2][2]) {
            System.out.println("Wygrał: " + plansza[2][0]);
            return true;
        }  else if(plansza[0][0] != '.' 
                && plansza[0][0] == plansza[1][0] 
                && plansza[1][0] == plansza[2][0]) {
            System.out.println("Wygrał: " + plansza[0][0]);
            return true;
        }  else if(plansza[0][1] != '.' 
                && plansza[0][1] == plansza[1][1] 
                && plansza[1][1] == plansza[1][2]) {
            System.out.println("Wygrał: " + plansza[0][1]);
            return true;
        }  else if(plansza[0][2] != '.' 
                && plansza[0][2] == plansza[1][2] 
                && plansza[1][2] == plansza[2][2]) {
            System.out.println("Wygrał: " + plansza[0][2]);
            return true;
        } else  if(plansza[0][0] != '.' 
                && plansza[0][0] == plansza[1][1] 
                && plansza[1][1] == plansza[2][2]) {
            System.out.println("Wygrał: " + plansza[0][0]);
            return true;
        } else  if(plansza[0][2] != '.' 
                && plansza[0][2] == plansza[1][1] 
                && plansza[1][1] == plansza[2][0]) {
            System.out.println("Wygrał: " + plansza[0][2]);
            return true;
        }
        return false;
    }
        
}

public class Zadanie129 {

    public static void main(String[] args) {
        Gra gra = new Gra();
        gra.run();
    }

}
