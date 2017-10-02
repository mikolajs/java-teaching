
public class Main {

	public static void main(String[] args) {
		long deltaTime = 0;
		deltaTime = System.currentTimeMillis();
		System.out.println("Liczenie w pętli");
		PrimitivePrimes pp = new PrimitivePrimes();
		deltaTime = System.currentTimeMillis() - deltaTime;
		System.out.println("Time " + deltaTime );
		System.out.println("liczb pierwszych: " + pp.howManyPrimes());
		pp.print(50000, 50050);
		deltaTime = System.currentTimeMillis();
		System.out.println("Sito Eratostenesa");
		Sieve s = new Sieve(1000_000);
		deltaTime = System.currentTimeMillis() - deltaTime;
		System.out.println("Time " + deltaTime );
		System.out.println("liczb pierwszych: " + s.howManyPrimes());
		s.print(50000, 50050);
		deltaTime = System.currentTimeMillis();
		System.out.println("Super sito Eratostenesa");
		SuperSieve ss = new SuperSieve();
		deltaTime = System.currentTimeMillis() - deltaTime;
		System.out.println("Time " + deltaTime );
		System.out.println("liczb pierwszych: " + ss.count());
		ss.print(50000, 50050);
		
	}
	
}
