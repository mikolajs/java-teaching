
public class PierwszePrymitywne {
	private int[] pierwsze;
	private int index = -1;
	final int ZAKRES = 1000_000;
	final int MAX =(int) (ZAKRES * Math.log(ZAKRES)) + 1000;
	public PierwszePrymitywne() {
		pierwsze = new int[ZAKRES];
		licz();
	}
	
   private void licz() {
	   boolean pierwsza;
	   for(int i = 2; i < ZAKRES; i++) {
		   pierwsza = true;
		   for(int j = 2; j <= (int) Math.sqrt(i); j++) {
			   if(i % j == 0) {
				   pierwsza = false;
				   break;
			   }
		   }
		   if(pierwsza) {
			   index++;
			   pierwsze[index] = i;
			   
		   }
	   }
   }
   
   public int ilePierwszych() {
	   return index + 1;
   }
   
   public void wypisz(int start, int koniec) {
	   for(int i = 0; i <= index; i++ ) {
		   if(pierwsze[i] >= koniec) break;
		   if(pierwsze[i] > start) System.out.print(pierwsze[i] +  " ");
	   }
	   System.out.println(" koniec");
   }
}
