package pl.edu.osp;

public class Rekurencja {

	
	public static void main(String[] args) {
		Rekurencja r = new Rekurencja();
		System.out.println(r.silnia2(1, 5));
//		long[] tab = new long[] {2, 3, 5, 6, 7, 0, 1};
//	assert r.horner(2, tab, tab.length -1) == r.hornerIter(2, tab);
		System.out.println("4 do potęgi 5 = " +r.pot(4, 5));
		System.out.println("4 do potęgi 5 = " +r.potO(1, 4, 5));
		System.out.println("4 do potęgi 0 = " +r.pot(4, 0));
		System.out.println("4 do potęgi 0 = " +r.potO(1, 4, 0));
		
		long n = 73;
		long t1 = System.nanoTime();
		
		System.out.println("iteracja fib = " + r.fib(n));
		System.out.println("czas: " + (System.nanoTime() - t1));
		t1 = System.nanoTime();
		System.out.println("rekurencja fib = " + r.fibRec(n));
		System.out.println("czas: " + (System.nanoTime() - t1));
		t1 = System.nanoTime();
		System.out.println("binet binet =" + r.binet(n));
		System.out.println("czas: " + (System.nanoTime() - t1));
		t1 = System.nanoTime();
		
		System.out.println("Tribonacci " + r.tribonacci(10));
	}
	
	public long silnia1(long n) {
		if(n < 2) return 1;
		else return n*silnia1(n-1);
	}
	
	public long silnia2(long s, long n) {
		if(n < 2) return  s;
		else return silnia2(s*n, n -1);
	}
	
	public long pot(long a, long n) {
		if(n  < 1) return 1;
		else return a*pot(a, n-1);
	}
	
	public long potO(long p, long a, long n) {
		if(n == 0 ) return p;
		else return potO(p*a, a, n - 1);
	}
	
	
	public long fib(long n) {
		if(n == 0) return 0;
		if(n == 1) return 1;
		else {
			long f1 = 0;
			long f2 = 1;
			long tmp = 0;
			for(long i = 2; i <= n; i++) {
				tmp = f2;
				f2 += f1;
				f1 = tmp;
			}
			return f2;
		}
	}
	
	
	public long fibRec(long n) {
		if(n == 0) return 0;
		if(n == 1) return 1;
		else return fib(n-2) + fib(n -1);
	}
	
	
	public long binet(long n) {
		double sqrt5 = Math.sqrt(5.0);
		return (long) ((1.0/sqrt5)*
				(Math.pow((1 + sqrt5)/2.0,  (double) n)
						- Math.pow((1 - sqrt5)/2.0,  (double) n))) ;
		
	}
	
	
	public long tribonacci(long n) {
		if(n == 0) return 0;
		if(n == 1) return 0;
		if(n == 2) return 1;
		else return tribonacci(n-3) + tribonacci(n-2) + tribonacci(n -1);
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
