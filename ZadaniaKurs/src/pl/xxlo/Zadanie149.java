package pl.xxlo;

import java.util.Date;
import java.util.GregorianCalendar;

class Pesel {
    long pesel;
    public Pesel(long pesel) {
        this.pesel = pesel;
    }
    public Pesel(String pesel) {
        this.pesel = Long.valueOf(pesel);
    }
    
    public boolean isCorrect() {
        return isOK();
    }
    
    public boolean isMale() {
        int g = (int) ((pesel / 10L) % 10L);
        return g % 2 != 0;
    }
    
    public String born() {
        System.out.println(pesel);
        int year = (int) (pesel / 1000_000_000L);
        int month = (int) (pesel / 10_000_000L) % 100;
        if(month > 12) {
            month -= 20;
            year += 2000;
        } else year += 1900;
        int day = (int) (pesel / 100_000L) % 100;
        return String.format("%2d-%2d-%4d", day, month, year);
    }
    
    //zwracający numer PESEL, informację o dacie urodzenia i
    //płci w jednej linii, lub informacje o niepoprawności peselu.
    public String toString() {
        return "";
    }
    
    private boolean isOK(){
        int cipher = 0;
        int weight[] = {3, 1, 9, 7, 3, 1, 9, 7, 3, 1};
        long p = pesel / 10;
        int sum = 0;
        for(int i = 0; i < 10; i++) {
            cipher = (int) (p % 10L);
            p /= 10L;
            sum += cipher*weight[i];
        }
        sum %= 10;
        if(sum != 0) {
            sum = 10 - sum;
        }
        return sum == pesel % 10;
    }
}

public class Zadanie149 {
    
    public static void main(String[] args) {
        long p1 = 75121968629L;
        long p2 = 75121968624L;
        long p3 = 1252004567L;
        Pesel pesel1 = new Pesel(p1);
        Pesel pesel2 = new Pesel(p2);
        Pesel pesel3 = new Pesel(p3);
        assert pesel1.isCorrect() == true;
        assert pesel2.isCorrect() == false;
        assert pesel1.isMale() == false;
        System.out.println(pesel1.born());
        System.out.println(pesel3.born());
        assert pesel1.born().equals("19-12-1975");
        //assert pesel3.born() == "20-05-2001";
    }
    

}
