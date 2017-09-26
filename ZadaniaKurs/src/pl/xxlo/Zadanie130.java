package pl.xxlo;

public class Zadanie130 {
	
	final int rFlag = 0x00ff0000;
	final int gFlag = 0x0000ff00;
	final int bFlag = 0x000000ff;
	
	public static void main(String[] args) {
		Zadanie130 z = new Zadanie130();
		z.test();
		z.a();
		z.b();
	
	}
	
	public void a() {
		int c = 1_023_745;
		System.out.printf("kodujemy liczbę %d RGB(%d, %d, %d) \n", c, getRed(c), getGreen(c), getBlue(c));
	}
	
	public void b() {
		System.out.println("RGB(125, 231, 43) = " + codeRGB(125, 231, 43));
	}
	
	private int codeRGB(int r, int g, int b) {
		return b + (g << 8) + (r << 16);
	}
	
	private int getRed(int color) {
		return (rFlag & color) >> 16;
	}
	private int getGreen(int color) {
		return (gFlag & color) >> 8;
	}
	private int getBlue(int color) {
		return (bFlag & color);
	}
	
	public void test() {
		int c = 0x00f00a10;
		System.out.println("Powinno być RGB(240, 10, 16)");
		System.out.printf("A jest: RGB(%d, %d, %d) \n", getRed(c), getGreen(c), getBlue(c));
		System.out.println("Kodowanie ponowne było " + c + " i jest " + codeRGB(240, 10, 16));
	}
}
