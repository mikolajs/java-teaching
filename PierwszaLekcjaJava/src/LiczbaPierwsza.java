

public class LiczbaPierwsza {

    public static void main(String[] args) {
       LiczbaPierwsza pierwsza = new LiczbaPierwsza();
       for(int i = 2; i < 500; i++)
           if(pierwsza.czyPierwsza(i))
               System.out.print(i + " ");

    }

    public boolean czyPierwsza(int p) {
        for(int i = 2; i <= (int) Math.sqrt(p); i++)
          if(p % i == 0) return false;
        return true;
    }
    
    public int[] sito(int max) {
        boolean[] tab = new boolean[max + 1];
        for(int i = 2; i < tab.length; i++)
            tab[i] = true;
        
        int j = 0;
        for(int i = 2; i <= Math.sqrt(max); i++) {
            j = i;
            while(true) {
                j += i;
                if(j <= max) tab[j] = false;
                else break;
            }
        }
        int nr = 0;
        for(int i = 2; i < tab.length; i++)
            if(tab[i]) nr++;
        
        int[] pierwsze = new int[nr];
        
        nr = 0;
        for(int i = 2; i < tab.length; i++) {
           if(tab[i]) {
               pierwsze[nr] = i;
           }
            
        }
        return pierwsze;
    }
    
}
