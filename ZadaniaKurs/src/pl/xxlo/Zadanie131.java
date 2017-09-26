package pl.xxlo;

/*
 * 

Flagi bitowe służą do ustawiania w jednej zmiennej wielu opcji. 
Do ich odczytania należy użyć operatorów bitowych. 
Definiujemy flagi służące do zapisu w jednym bajcie opcji wyświetlania czcionki. 
I tak:
1 bit – podkreślenie, 2 bit - nadkreślenie, 3 bit – przekreślenie,  
4 bit - pogrubienie, 5 bit - pochylenie, 6 bit indeks górny, 7 bit - indeks dolny, 
8 bit -  kapitalik,
A) utwórz zmienną (byte) zawierającą  flagi: nadkreślenie, pogrubienie, kapitalik
B) odczytaj jakie formatowanie zakodowano w liczbie 83

 */

public class Zadanie131 {
	
	public enum FONT {
		PODKRESL(0b10000000),
		NADKRESL(0b10000000),
		PRZEKRESL(0b10000000),
		POGRUB(0b10000000),
		POCHYL(0b10000000),
		INDEKS_G(0b10000000),
		INDEKS_D(0b10000000),
		KAPITALIK(0b10000000);
		byte color;
		private FONT(int c) {
			color = (byte) c;
		}
	}

	public static void main(String[] args) {
		Zadanie131 z = new Zadanie131();
		z.test();
		z.a();
		z.b();
	}
	
	public void a() {
		
	}
	
	public void b() {
		
	}
	
	private code(int p, )
	
	public void test() {
		
	}

}
