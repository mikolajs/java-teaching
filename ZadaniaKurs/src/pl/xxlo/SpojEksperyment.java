package pl.xxlo;

import java.util.Scanner;

class P {
    int x;
    int t;
}

public class SpojEksperyment {
    
    public boolean isAbove(P p1, P p2) {
        if(Math.abs(p1.x - p2.x) <= Math.abs(p1.t - p2.t)) return false;
        else return true;
    }
    
    public P upBottom(P bottom, P drop) {
        if(Math.abs(bottom.x - drop.x) <= Math.abs(bottom.t - drop.t)) return bottom;
        else return new P(); ///poprawić!!!!!!!!!!!!!!!!
    }

    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in); 
      int d = sc.nextInt();
      int n = sc.nextInt();
      int tl = Integer.MAX_VALUE;
      int tr = Integer.MAX_VALUE;
      //P[] drops = new P[n];
      P[] bottoms = new P[n+1];
      int x, t;
      int j;
      for(int i = 0; i < n; i++) {
          x = sc.nextInt();
          t = sc.nextInt();
          j = 0;
          while(j <= i) {
              
          }
      }
      sc.close();
    }

}
