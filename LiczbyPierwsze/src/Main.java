
public class Main {

	public static void main(String[] args) {
		long deltaTime = 0;
		deltaTime = System.currentTimeMillis();
		System.out.println("Liczenie w pętli");
		PierwszePrymitywne pp = new PierwszePrymitywne();
		deltaTime = System.currentTimeMillis() - deltaTime;
		System.out.println("Time " + deltaTime );
		System.out.println("liczb pierwszych: " + pp.ilePierwszych());
		pp.wypisz(1000, 1100);
		deltaTime = System.currentTimeMillis();
		System.out.println("Sito Eratostenesa");
		Sito s = new Sito(1000_000);
		deltaTime = System.currentTimeMillis() - deltaTime;
		System.out.println("Time " + deltaTime );
		System.out.println("liczb pierwszych: " + s.ilePierwszych());
		s.wypisz(1000, 1100);
		deltaTime = System.currentTimeMillis();
		System.out.println("Super sito Eratostenesa");
		SuperSieve ss = new SuperSieve();
		deltaTime = System.currentTimeMillis() - deltaTime;
		System.out.println("Time " + deltaTime );
		System.out.println("liczb pierwszych: " + ss.count());
		ss.print(1000, 1100);
		
	}
	
}
