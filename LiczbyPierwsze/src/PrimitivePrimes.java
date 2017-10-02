
public class PrimitivePrimes {
	private int[] primes;
	private int index = -1;
	final int RANGE = 1000_000;
	final int MAX =(int) (RANGE * Math.log(RANGE)) + 1000;
	public PrimitivePrimes() {
		primes = new int[RANGE];
		cout();
	}
	
   private void cout() {
	   boolean prime;
	   for(int i = 2; i < RANGE; i++) {
		   prime = true;
		   for(int j = 2; j <= (int) Math.sqrt(i); j++) {
			   if(i % j == 0) {
				   prime = false;
				   break;
			   }
		   }
		   if(prime) {
			   index++;
			   primes[index] = i;
			   
		   }
	   }
   }
   
   public int howManyPrimes() {
	   return index + 1;
   }
   
   public void print(int start, int end){
	   for(int i = 0; i <= index; i++ ) {
		   if(primes[i] >= end) break;
		   if(primes[i] > start) System.out.print(primes[i] +  " ");
	   }
	   System.out.println(" koniec");
   }
}
