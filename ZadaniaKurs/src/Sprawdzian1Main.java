import java.util.Random;

class Sprawdzian1 {
    public int sum(int[] tab) {
        int s = 0;
        for(int e: tab) {
            s += e;
        }
        return s;
    }
    
    public int avg(int[] tab) {
        return sum(tab)/tab.length;
    }
    
    public int[] maxMin(int[] tab) {
        int[] maxmin = new int[2];
        maxmin[0] = tab[0];
        maxmin[1] = tab[0];
        for(int i = 1; i < tab.length; i++) {
            if(maxmin[0] < tab[i]) maxmin[0] = tab[i];
            else if(maxmin[1] > tab[i]) maxmin[1] = tab[i];
        }
        return maxmin;
    }
    
    
    public int superAvg(int[] tab) {
        int R = 0;
        int A = 0;
        int s = tab.length;
        for(int e : tab) {
            A += e / s;
            R += e % s;
            A += R / s; 
            R %= s;
        }
        System.out.println("Średnia " + A + " i " + R + "/" + s);
        return A;
    }
}

public class Sprawdzian1Main {
    public static void main(String[] args) {
        int[] tab = {3,1,2,4,5,11,7,8,9,10,6};
        Sprawdzian1 s = new Sprawdzian1();
        
        System.out.println("Suma: " + s.sum(tab));
        System.out.println("Średnia: " + s.avg(tab));
        int[] mm = s.maxMin(tab);
        System.out.println("Maksimum: " + mm[0] + " minimum: " + mm[1]);
        
        int[] tab2 = new int[100];
        Random r = new Random();
        for(int i = 0; i < tab2.length; i++) tab2[i] = r.nextInt(Integer.MAX_VALUE);
        for(int i = 0; i < tab2.length; i++) if(tab2[i] < 0) 
            System.out.println(tab2[i]);
        s.superAvg(tab2);
        s.superAvg(tab);
    }
}
