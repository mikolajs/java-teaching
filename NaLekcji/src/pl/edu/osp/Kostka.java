package pl.edu.osp;
import java.util.Random;

public class Kostka {
    Random r;
    
    public Kostka() {
        r = new Random();
    }
    
    public void losuj(int ile) {
        int[] liczby = new int[7];
        for(int i = 0; i < ile; i++) {
           int a = r.nextInt(6) + 1;
           liczby[a]++;
        }
        for(int i = 1; i < liczby.length; i++) {
            System.out.println(i + " : " + liczby[i]);
        }
        
    }

}
