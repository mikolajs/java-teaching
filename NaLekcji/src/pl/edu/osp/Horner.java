package pl.edu.osp;

public class Horner {
	
	public long horner(int x, int[] a) {
		if(a.length == 0) return 0L;
		long fx = a[0];
		for(int i = 1; i < a.length; i++) {
			fx *=x;
			fx += a[i];
		}
		return fx;
	}
	
	public static void main(String[] args) {
		Horner h = new Horner();
		//1*2^3 + 3*2 + 4 = 8 + 6 + 4 = 18
		System.out.println(h.horner(2, new int[]{1, 0, 3, 4}));
	}

}
