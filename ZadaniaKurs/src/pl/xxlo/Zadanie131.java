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
		NADKRESL(0b01000000),
		PRZEKRESL(0b00100000),
		POGRUB(0b00010000),
		POCHYL(0b00001000),
		INDEKS_G(0b00000100),
		INDEKS_D(0b10000010),
		KAPITALIK(0b10000001);
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
		System.out.println("Flagi nadkreślenie, pogrubienie, kapitalik: " + 
	    code(FONT.NADKRESL, FONT.POGRUB, FONT.KAPITALIK));
	}
	
	public void b() {
		decode((byte) 83);
	}
	
	private byte code(FONT... props) {
		int c = 0;
		for(FONT f : props) {
			c |= f.ordinal();
		}
		return (byte) c;
	}
	
	private void decode(byte c) {
		int code = (int )c;
		for(FONT f: FONT.values()) {
			if((code & f.ordinal()) != 0) 
				System.out.println(f.toString());
		}
	}
	
	public void test() {
		
	}

}
