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
        return "";
    }
    
    public Date bornDate() {
        int year = (int) (pesel / 1000_000_000L);
        int month = (int) (pesel / 10_000_000L) % 100;
        if(month > 12) {
            month -= 20;
            year += year;
        } else year += 1900;
        int day = (int) (pesel / 100_000L) % 100;
        return new GregorianCalendar(year, month, day).getTime();
    }
    
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
        Pesel pesel1 = new Pesel(p1);
        Pesel pesel2 = new Pesel(p2);
        assert pesel1.isCorrect() == true;
        assert pesel2.isCorrect() == false;
        assert pesel1.isMale() == false;
        assert new Date(1975, 12, 19).getTime() == pesel1.bornDate().getTime();
    }
    

}
