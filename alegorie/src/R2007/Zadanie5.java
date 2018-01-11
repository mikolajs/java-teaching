package R2007;
//zadanie uproszczone werdług treści z osp. 
// w orginale test czy suma cyfr zapisu binarnego jest piewsza
//Ile jest liczb w przedziale <100,10000>, których suma cyfr jest liczbą pierwszą?
//Czy suma wszystkich liczb „super B pierwszych” z przedziału <100,10000> jest liczbą
//pierwszą?
public class Zadanie5 {
	boolean[] sieve;
	final int SIZE = 100001;
	
	public Zadanie5(){
		sieve = new boolean[SIZE];
		fillSieve();
//		test();
//		test2();
	}
	
	public int superPrimesCount(int s, int e){
		int primes = 0;
		for(int i = s; i <= e; i++){
			if(!sieve[i] && !sieve[sumFigures(i)]) primes++;
		}
		return primes;
	}
	
	private int sumFigures(int numb){
		int sum = 0;
		while(numb > 0){
			sum += numb % 10;
			numb /= 10;
		}
		return sum;
	}
	
	private void fillSieve(){
		int j = 2;
		int w = 0;
		for(int i = 2; i < SIZE; i++){
			j = 2;
			w = i*j;
			if(!sieve[i]){
				while(w < SIZE){
					sieve[w] = true;
					w = i*j++;
				}
			}
		}
	}
	
//    private void test(){
//    	assert(sieve[2] == false);
//    	assert(sieve[97] == false);
//    	assert(sieve[19] == false);
//    	assert(sieve[81] == true);
//    	assert(sieve[93] == true);
//    }
//    
//    private void test2(){
//    	assert(sumFigures(12345) == 15);
//    	assert(sumFigures(449) == 17);
//    	assert(superPrimesCount(2,11) == 5);
//    	assert(superPrimesCount(10,20) == 1);
//    }
}
