package pl.edu.osp;

public class Silnia {
    
    public long silnia(int n) {
        long s = 1;
        for(int i = n; i > 1; i--) {
            s *= i;
        }
        
        return s;
    }
    

}
