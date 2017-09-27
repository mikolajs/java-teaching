package pl.xxlo;



public class Zadanie131 {
	
	public enum FONT {
		PODKRESL(0b10000000),
		NADKRESL(0b01000000),
		PRZEKRESL(0b00100000),
		POGRUB(0b00010000),
		POCHYL(0b00001000),
		INDEKS_G(0b00000100),
		INDEKS_D(0b00000010),
		KAPITALIK(0b00000001);
		int color;
		private FONT(int c) {
			color =  c;
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
			c |= (int) f.color;
		}
		return (byte) c;
	}
	
	private void decode(byte c) {
		
		int code = (int )c;
		System.out.println("Liczba: " + c + ": ");
		for(FONT f: FONT.values()) {
			if((code & f.color) != 0) 
				System.out.println(f.toString());
		}
		System.out.println("KONIEC");
	}
	
	public void test() {
		System.out.println(">>>TEST START");
		System.out.println(Integer.toString(102, 2));
		assert code(FONT.NADKRESL, FONT.PRZEKRESL, FONT.INDEKS_G, FONT.INDEKS_D) == (byte) 102;
		decode((byte) 102);
		decode((byte) -1);
		System.out.println(">>>TEST END");
	}

}
