package pl.edu.osp;


class Main
{
	
	 private int a;
     private int b;
     private int c;
     public int suma(int[] tab) {
         this. a = 0;
         for ( int o : tab) {
        
          a+= o;
         }
         return a;
        
             }
          
        
         public int max(int[] tab) {
             this. b = 0;
             for (int x : tab) {
                 if (b < x) b = x;
             }
         return b;
     }
    
     public int avg(int[] tab) {
         this. c = 0;
         for (int p : tab)
         c += p;
     return c/(tab.length);
     }

     public static void main(String args[]) {
         int tablica[] = {10, 14, 32, 34, 1, 98, 6, 4, 3, 43};
         int result = 0;
         int i;
         for(i=0; i< 10; i++)
         result = result + tablica[i];
         System.out.println("suma = " + result);
         System.out.println("AVG = " + result/10);
         
         int max;
         max = tablica [0];
         for(int j= 1; j < 10; j++)
         {
             if(tablica[j] > max)
                 max = tablica[j];
             System.out.println("MAX = " + max);
                     
         }
         
         
         
     }
//	public static void main(String[] args) {
//		int[] tab = {1, 3, 5, 7, 9, 11, 13};
//		Main m = new Main();
//		assert m.max(tab) == 13;
//		assert m.avg(tab) == 7;
//		assert m.suma(tab) == 49;
//		System.out.println("Is OK");
//	}
}



