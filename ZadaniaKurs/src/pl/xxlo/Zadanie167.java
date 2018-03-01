package pl.xxlo;

public class Zadanie167 {
	
	private long[] kod;

	public static void main(String[] args) {
		Zadanie167 zad = new Zadanie167(new long[]{3, 5, 6, 7, 8, 0, 1} );
		String zdanie = "ZDANIEDOZAKODOWANIA";
		System.out.println("Zakodowane: " + zad.koduj(zdanie));
		System.out.println("Odkodowane: " + zad.odkoduj(zad.koduj(zdanie)));

	}
	
	public Zadanie167(long[] kod) { this.kod = kod;}

	public String koduj(String zdanie) {
		String zakodowane = "";
		int l;
		for(int i = 0; i < zdanie.length(); i++) {
			l = (int) zdanie.charAt(i) +(int) horner(i, kod) % 26 ;
			if(l > 90) l -= 26;
			zakodowane += (char) l;
		}
		return zakodowane;
	}

	public String odkoduj(String zakodowane) {
		String zdanie = "";
		int l;
		for(int i = 0; i < zakodowane.length(); i++) {
			l = (int) zakodowane.charAt(i)  -(int) horner(i, kod) % 26 ;
			if(l < 65) l += 26;
			zdanie += (char) l;
		}
		return zdanie;
	}
	
	public long horner(long x, long[] a) {
		if(a.length == 0) return 0L;
		long fx = a[0];
		for(int i = 1; i < a.length; i++) {
			fx *=x;
			fx += a[i];
		}
		return fx;
	}
}
