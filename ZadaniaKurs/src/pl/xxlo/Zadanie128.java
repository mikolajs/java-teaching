package pl.xxlo;

public class Zadanie128 {
	
	int[] tab;
	final int size;
	
	public static void main(String[] args) {
		Zadanie128 z = new Zadanie128(10);
//		z.test();
		z.drawBrutal();
		z.drawAdding();
	}
	
	public Zadanie128(int s) {
		size = s;
		tab = new int[size + 5];
	}
	
	public void drawBrutal() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j <= i; j++ ) {
				tab[j] = combine(i, j); 
			}
			drawTab(i);
		}
	}
	//not work
	public void drawAdding() {
		int prev = 0;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j <= i; j++ ) {
				if(j == 0 || j == i) {
					prev = tab[j];
					tab[j] = 1;
				}
				else {
					tab[j] += prev ;
					prev = tab[j] - prev;
				}
//				
			}
			drawTab(i);
		}
		
	}
	
	private void drawTab(int s) {
		for(int i = 0; i < 10 - s ; i++) System.out.print("   ");
		for(int i = 0; i <= s; i++) {
			if(tab[i] / 100 > 0) System.out.print("  " + tab[i] + " ");
			else if(tab[i] / 10 > 0) System.out.print("  " + tab[i] + "  ");
			else System.out.print("   " + tab[i] + "  ");
		}
		System.out.println();
	}
	
	private int factorial(int n) {
		int s = 1; 
		if(n <= 0) return 1;
		while( n >  1) {
			s *= n;
			n--;
		}
		return s;
	}
	
	private int combine(int n, int k) {
		return factorial(n)/ (factorial(n - k)*factorial(k));
	}
	
//	public void test() {
//		assert factorial(5) == 120;
//		assert factorial(1) == 1;
//		assert factorial(0) == 1;
//		System.out.println("Combine 1, 1 = " + combine(1, 1));
//	}

}
