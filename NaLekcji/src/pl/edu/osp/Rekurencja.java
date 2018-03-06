package pl.edu.osp;

public class Rekurencja {

	
	public static void main(String[] args) {
		Rekurencja r = new Rekurencja();
		System.out.println(r.silnia2(1, 5));
		long[] tab = new long[] {2, 3, 5, 6, 7, 0, 1};
		assert r.horner(2, tab, tab.length -1) == r.hornerIter(2, tab);
	}
	
	public long silnia1(long n) {
		if(n < 2) return 1;
		else return n*silnia1(n-1);
	}
	
	public long silnia2(long s, long n) {
		if(n < 2) return  s;
		else return silnia2(s*n, n -1);
	}
	
	public long horner(long x, long tab[], int poz) {
		if(poz == 0) return tab[poz];
		else return horner(x, tab, poz-1)*x + tab[poz];
	}
	
	public long hornerIter(long x, long[] a) {
		if(a.length == 0) return 0L;
		long fx = a[0];
		for(int i = 1; i < a.length; i++) {
			fx *=x;
			fx += a[i];
		}
		return fx;
	}
	
}
