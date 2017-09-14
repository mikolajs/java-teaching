
public class PierwszePrymitywne {
	private int[] pierwsze;
	private int index = 0;
	public PierwszePrymitywne() {
		pierwsze = new int[10000];
		licz();
	}
	
   private void licz() {
	   boolean pierwsza = false;
	   for(int i = 2; i < 1000_000; i++) {
		   pierwsza = false;
		   for(int j = 2; j <= 1000; j++) {
			   if(i % j == 0) {
				   pierwsza = true;
				   pierwsze[index] = i;
				   index++;
				   break;
			   }
		   }
	   }
   }
   
   public int ilePierwszych() {
	   return pierwsze.length;
   }
}
